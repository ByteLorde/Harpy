import nova.NovaBot;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Pie Shell Maker", description = "Makes the pie shells", author = "Refreshed", version = 1.01, category = Category.MISC, image = "")
public class PieShellMakerBot extends NovaBot {

    public PieShellMakerBot() {
        super(PieShellMakerBot.class.getName());

        String[] scripts = new String[] {
            "WithdrawMaterials",
            "MakeShells",
            "DepositShells"
        };

        this.loadScripts(scripts);
        this.enable();
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
