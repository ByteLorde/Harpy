package scripts.WithdrawMaterials;

import constants.ItemID;
import nova.NovaScript;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;

public class WithdrawMaterials extends NovaScript {
    public final int WITHDRAW_AMOUNT = 14;

    public WithdrawMaterials() {
        super(WithdrawMaterials.class.getName());
    }

    @Override
    public int tick() {
        log("Withdrawing mats");
        if ( !Bank.isOpen() ) {
            Bank.openClosest();
            sleepWhile(() -> !Bank.isOpen(), Calculations.random(3442, 4657));
        }

        if(!Bank.contains(ItemID.PIE_DISH) || !Bank.contains(ItemID.PASTRY_DOUGH)) {
            log("Out of mats: Stopping.");
            stop();
        }

        Bank.withdraw(ItemID.PIE_DISH, WITHDRAW_AMOUNT);
        sleep(344, 511);
        Bank.withdraw(ItemID.PASTRY_DOUGH, WITHDRAW_AMOUNT);
        sleep(407, 633);

        Bank.close();
        sleep(201, 342);

        return 500;
    }

    @Override
    public boolean isComplete() {
        return (Inventory.containsAll(ItemID.PIE_DISH, ItemID.PASTRY_DOUGH));
    }
}
