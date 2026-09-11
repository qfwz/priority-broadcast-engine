package com.threedolphins.broadcast.bean;

import com.threedolphins.broadcast.model.Customer;
import com.threedolphins.broadcast.model.MessageStatus;
import com.threedolphins.broadcast.service.BroadcastService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class BroadcastBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BroadcastService broadcastService;

    private List<Customer> customers;
    private List<Customer> selectedCustomers;
    private boolean running;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();

        customers.add(new Customer(1L, "Agata Syarief", "+62 812-1000-0001"));
        customers.add(new Customer(2L, "Bidadari Purnamasari", "+62 812-1000-0002"));
        customers.add(new Customer(3L, "Citra Citratra", "+62 812-1000-0003"));
        customers.add(new Customer(4L, "Dewi Malaika", "+62 812-1000-0004"));
        customers.add(new Customer(5L, "Egi Regiawan", "+62 812-1000-0005"));
        customers.add(new Customer(6L, "Fawwaz Aminullah", "+62 812-1000-0006"));
        customers.add(new Customer(7L, "Gunadi Hariadi", "+62 812-1000-0007"));
        customers.add(new Customer(8L, "Hariawan Gunawan", "+62 812-1000-0008"));
        customers.add(new Customer(9L, "Indra Jaka", "+62 812-1000-0009"));
        customers.add(new Customer(10L, "Joko Indro", "+62 812-1000-0010"));

        selectedCustomers = new ArrayList<>();
        running = false;
    }

    public void startBroadcast() {
        if (running || selectedCustomers == null || selectedCustomers.isEmpty()) {
            return;
        }

        running = true;

        resetSelectedCustomers();

        broadcastService.startBroadcast(
                new ArrayList<>(selectedCustomers),
                () -> running = false
        );
    }

    private void resetSelectedCustomers() {
        for (Customer customer : selectedCustomers) {
            customer.setStatus(MessageStatus.PENDING);
            customer.setLastError(null);
        }
    }

    public void tick() {
        // Polling method used to trigger JSF AJAX updates.
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Customer> getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(List<Customer> selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }

    public boolean isRunning() {
        return running;
    }

    public void broadcastFinished() {
        running = false;
    }
}