package com.practice.demo.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.function.Consumer;

@Component
public class TransactionTool {

    private static final Logger log = LoggerFactory.getLogger(TransactionTool.class);

    @Autowired
    private PlatformTransactionManager transactionManager;

    public boolean transact(Consumer consumer) {
        TransactionDefinition txDefinition = createTxDefinition(-1);
        return this.transact(consumer, txDefinition);
    }

    public boolean transact(Consumer consumer, int isolation) {
        TransactionDefinition txDefinition = createTxDefinition(isolation);
        return this.transact(consumer, txDefinition);
    }

    public boolean transact(Consumer consumer, int isolation, int propagation) {
        TransactionDefinition txDefinition = createTxDefinition(isolation, propagation);
        return this.transact(consumer, txDefinition);
    }

    public boolean transact(Consumer consumer, int isolation, int propagation, Class rollbackFor) {
        if (rollbackFor == null) {
            rollbackFor = Exception.class;
        }
        TransactionDefinition txDefinition = createTxDefinition(isolation, propagation);
        return this.transact(consumer, txDefinition, rollbackFor);
    }

    public TransactionDefinition createTxDefinition(int isolation) {
        return createTxDefinition(isolation, 0, -1);
    }

    public TransactionDefinition createTxDefinition(int isolation, int propagation) {
        return createTxDefinition(isolation, propagation, -1);
    }

    public TransactionDefinition createTxDefinition(int isolation, int propagation, int timeout) {
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setIsolationLevel(isolation);
        txDefinition.setPropagationBehavior(propagation);
        txDefinition.setTimeout(timeout);
        return txDefinition;
    }

    public boolean transact(Consumer consumer, TransactionDefinition txDefinition) {
        return this.transact(consumer, txDefinition, Exception.class);
    }

    public boolean transact(Consumer consumer, TransactionDefinition txDefinition, Class rollbackFor) {
        TransactionStatus txStatus = transactionManager.getTransaction(txDefinition);
        try {
            consumer.accept(null);
            transactionManager.commit(txStatus);
            return true;
        } catch (Exception e) {
            if (e.getClass().equals(rollbackFor)) {
                transactionManager.rollback(txStatus);
            }
            log.error("执行编程式事务时异常", e);
            return false;
        }
    }

}
