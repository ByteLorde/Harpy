package scripts.MakeShells;

import constants.ItemID;
import nova.NovaScript;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

public class MakeShells extends NovaScript {

    public MakeShells() {
        super(MakeShells.class.getName());
    }

    @Override
    public int runScript() {
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
        return 500;
    }

    @Override
    public boolean isComplete() {
        return (!Inventory.contains(ItemID.PIE_DISH) || !Inventory.contains(ItemID.PASTRY_DOUGH));
    }
}
