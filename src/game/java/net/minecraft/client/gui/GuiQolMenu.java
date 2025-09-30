package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.QolSettings; // Import the QolSettings class

public class GuiQolMenu extends GuiScreen {

    private GuiScreen parent;
    private Minecraft mc;

    public GuiQolMenu(GuiScreen parent) {
        this.parent = parent;
        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void initGui() {
        this.buttonList.clear();

        // Fullbright Button
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4, 
                QolSettings.fullbright ? "Fullbright: ON" : "Fullbright: OFF"));

        // No Particles Button
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 24, 
                QolSettings.noParticles ? "No Particles: ON" : "No Particles: OFF"));

        // No Hurtcam Button
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 48, 
                QolSettings.noHurtCam ? "No HurtCam: ON" : "No HurtCam: OFF"));

        // Back Button to return to the previous screen
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 72, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                toggleFullbright(button);
                break;
            case 2:
                toggleNoParticles(button);
                break;
            case 3:
                toggleNoHurtcam(button);
                break;
            case 4:
                this.mc.displayGuiScreen(this.parent); // Go back to the previous screen
                break;
        }
    }

    private void toggleFullbright(GuiButton button) {
        QolSettings.toggleFullbright();
        button.displayString = QolSettings.fullbright ? "Fullbright: ON" : "Fullbright: OFF";
    }

    private void toggleNoParticles(GuiButton button) {
        QolSettings.toggleNoParticles();
        button.displayString = QolSettings.noParticles ? "No Particles: ON" : "No Particles: OFF";
    }

    private void toggleNoHurtcam(GuiButton button) {
        QolSettings.toggleNoHurtCam();
        button.displayString = QolSettings.noHurtCam ? "No HurtCam: ON" : "No HurtCam: OFF";
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Eclipse+ Settings", this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        this.mc.gameSettings.saveOptions();  // Save settings when the menu is closed
    }
}
