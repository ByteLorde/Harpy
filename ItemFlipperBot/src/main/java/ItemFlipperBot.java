import nova.SingleStateNovaBot;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;


@ScriptManifest(name = "Grand Exchange Bot", description = "", author = "Syndicate", version = 1.01, category = Category.MISC, image = "")
public class ItemFlipperBot extends SingleStateNovaBot {

    public ItemFlipperBot() {
        super(ItemFlipperBot.class.getName());

        String[] scriptLoadOrder = new String[]{
                "InterfaceWithGrandExchange"
        };

        this.loadScripts(scriptLoadOrder);
        this.enable();
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
