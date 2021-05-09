package scripts.DepositShells;

import constants.ItemID;
import nova.NovaScript;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;

public class DepositShells extends NovaScript {

    public DepositShells() {
        super(DepositShells.class.getName());
    }

    @Override
    public int tick() {
        log("Depositing pie shells");
        if (!Bank.isOpen()) {
            Bank.openClosest();
            sleep(242, 710);
        }

        if (Bank.isOpen()) {
            Bank.depositAllItems();
            sleepWhile(() ->
                            Inventory.contains(ItemID.PIE_SHELL),
                    Calculations.random(538, 720));
        }
        return 500;
    }

    @Override
    public boolean isComplete() {
        return Inventory.isEmpty();
    }
}
