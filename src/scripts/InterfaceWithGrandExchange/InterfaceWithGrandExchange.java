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

        if (GrandExchange.isReadyToCollect()) {
            GrandExchange.collect();
        }

        if (Inventory.contains(7936) || Inventory.contains(7937)) {
            GrandExchange.sellItem("Pure Essence", Inventory.count("Pure Essence"), 2);
        }

        if (!this.hasBuyOfferForItem("Pure Essence")) {
            GrandExchange.buyItem("Pure Essence", Math.min(1000000, Inventory.count("Coins")), 1);
        }



        return 3000;
    }

    @Override
    public boolean isComplete() {
        return !GrandExchange.isReadyToCollect();
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
                .anyMatch(item -> item.getName().equalsIgnoreCase(itemName) && item.isBuyOffer());
    }

    private double calculatePercentFulfilled(int current, int total) {
        return (double) current / total;
    }

}
