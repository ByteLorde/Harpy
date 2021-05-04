package scripts.InterfaceWithGrandExchange;


import nova.NovaScript;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.grandexchange.GrandExchange;

import java.util.Arrays;

public class InterfaceWithGrandExchange extends NovaScript {

    public InterfaceWithGrandExchange() {
        super(scripts.InterfaceWithGrandExchange.InterfaceWithGrandExchange.class.getName());
    }

    @Override
    public int runScript() {

        log("Script running: InterfaceWithGrandExchange");
        this.openGrandExchange();

        if (GrandExchange.isReadyToCollect()) {
            log("Ready to collect");
            GrandExchange.collect();
        }

        if (Inventory.contains(7936) || Inventory.contains(7937)) {
            log("Pure ess found in inventory!");
            GrandExchange.sellItem("Pure essence", Inventory.count("Pure essence"), 2);
        }

        if (!this.hasBuyOfferForItem("Pure essence")) {
            log("No buy offer for pure ess found");
            GrandExchange.buyItem("Pure essence", Math.min(1000000, Inventory.count("Coins")), 1);
        }

        return 3000;
    }

    private void openGrandExchange() {
        if (GrandExchange.isOpen()) {
            return;
        }
        GrandExchange.open();
    }

    @Override
    public boolean isComplete() {
        return false;
//        return !GrandExchange.isReadyToCollect();
    }

//    public boolean verifyBuyOffers() {
//
//        Arrays.stream(GrandExchange.getItems()).forEach(item -> {
//            if (item.isSellOffer()) {
//                return;
//            }
//
//            if ( item.getID() == 7936 ) {
//                log("Amount: " + item.getAmount());
//                log("Transferred Amount: " + item.getTransferredAmount());
//                double percentComplete = this.calculatePercentFulfilled(item.getAmount(), item.getTransferredAmount());
//                log("Percent Complete: " + percentComplete);
//            }
//
//        });
//
//        return false;
//    }

    public boolean hasBuyOfferForItem(String itemName) {
        return Arrays.stream(GrandExchange.getItems())
                .anyMatch(item -> {
                    log("Item from stream: " + item.getName());
                   return item.getName().equalsIgnoreCase(itemName) && item.isBuyOffer();
                });
    }

    private double calculatePercentFulfilled(int current, int total) {
        return (double) current / total;
    }

}
