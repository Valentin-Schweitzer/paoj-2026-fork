package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.CannotCancelFinalOrderException;
import com.pao.laboratory07.exercise1.exceptions.CannotRevertInitialOrderStateException;
import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderState currentState;
    private final List<OrderState> history;

    // Creează o comandă cu starea inițială primită
    public Order(OrderState initialState) {
        this.currentState = initialState;
        this.history = new ArrayList<>();
    }

    // Trece comanda în starea următoare
    public void nextState() throws OrderIsAlreadyFinalException {
        if (currentState.isFinalState()) {
            throw new OrderIsAlreadyFinalException();
        }

        // Salvăm starea curentă pentru undo
        history.add(currentState);

        if (currentState == OrderState.PLACED) {
            currentState = OrderState.PROCESSED;
        } else if (currentState == OrderState.PROCESSED) {
            currentState = OrderState.SHIPPED;
        } else if (currentState == OrderState.SHIPPED) {
            currentState = OrderState.DELIVERED;
        }

        System.out.println("Order state updated to: " + currentState);
    }

    // Anulează comanda dacă nu este deja finală
    public void cancel() throws CannotCancelFinalOrderException {
        if (currentState.isFinalState()) {
            throw new CannotCancelFinalOrderException();
        }

        // Salvăm starea curentă pentru undo
        history.add(currentState);

        currentState = OrderState.CANCELED;
        System.out.println("Order has been canceled.");
    }

    // Revine la starea anterioară
    public void undoState() throws CannotRevertInitialOrderStateException {
        if (history.isEmpty()) {
            throw new CannotRevertInitialOrderStateException();
        }

        // Luăm ultima stare salvată
        currentState = history.remove(history.size() - 1);
        System.out.println("Order state reverted to: " + currentState);
    }
}