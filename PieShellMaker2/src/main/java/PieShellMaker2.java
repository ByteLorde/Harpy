import constants.ItemID;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.widgets.WidgetChild;

@ScriptManifest(name = "Pie Shell Maker2", description = "Makes the pie shells2", author = "Refreshed", version = 1.01, category = Category.MISC, image = "")
public class PieShellMaker2 extends AbstractScript {

    private String state;
    public final int WITHDRAW_AMOUNT = 14;

    public final String WITHDRAWING = "withdrawing";
    public final String DEPOSITING = "depositing";
    public final String CRAFTING = "crafting";

    @Override
    public int onLoop() {
        switch (state) {
            case WITHDRAWING:
                withdrawMaterials();
                break;
            case CRAFTING:
                makeShells();
                break;
            case DEPOSITING:
                depositShells();
                break;
            default:
                state = DEPOSITING;
                return 0;
        }

        return 500;
    }

    @Override
    public void onStart() {
        setState();
    }

    private void setState() {
        if (Inventory.isEmpty()) {
            state = WITHDRAWING;
        } else if (Inventory.contains(ItemID.PIE_SHELL)) {
            state = DEPOSITING;
        } else {
            state = CRAFTING;
        }
    }

    private void withdrawMaterials() {
        log("Withdrawing mats");
        if (!Bank.isOpen()) {
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

        if (Inventory.containsAll(ItemID.PIE_DISH, ItemID.PASTRY_DOUGH)) {
            state = CRAFTING;
        }
    }

    private void makeShells() {
        log("Crafting pie shells");
        if (Inventory.contains(ItemID.PIE_DISH, ItemID.PASTRY_DOUGH)) {
            Inventory.getRandom(ItemID.PIE_DISH).useOn(ItemID.PASTRY_DOUGH);
            sleep(670, 1142);

            WidgetChild makeAllPiesWidget = Widgets.getWidget(270)
                .getChild(14);

            if (makeAllPiesWidget != null) {
                makeAllPiesWidget.interact();
                sleepWhile(() ->
                    Inventory.contains(ItemID.PIE_DISH) && Inventory.contains(ItemID.PASTRY_DOUGH),
                    Calculations.random(17201, 19511));
            }
        }

        if (!Inventory.contains(ItemID.PIE_DISH) || !Inventory.contains(ItemID.PASTRY_DOUGH)) {
            state = DEPOSITING;
        }
    }

    private void depositShells() {
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

        if (Inventory.isEmpty()) {
            state = WITHDRAWING;
        }
    }
}
