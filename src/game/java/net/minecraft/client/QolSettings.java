package net.minecraft.client;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class QolSettings {

    public static boolean fullbright = false;
    public static boolean noParticles = false;
    public static boolean noHurtCam = false;

    // Toggle Fullbright
    public static void toggleFullbright() {
        fullbright = !fullbright;
        applyFullbright();
    }

    // Toggle No Particles
    public static void toggleNoParticles() {
        noParticles = !noParticles;
        applyNoParticles();
    }

    // Toggle No HurtCam
    public static void toggleNoHurtCam() {
        noHurtCam = !noHurtCam;
        applyNoHurtCam();
    }

    // Apply Fullbright (gamma setting)
    public static void applyFullbright() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.gammaSetting = fullbright ? 1000.0F : 1.0F; // Apply Fullbright
        mc.gameSettings.saveOptions();
    }

    // Apply No Particles setting
    public static void applyNoParticles() {
        Minecraft mc = Minecraft.getMinecraft();
        // Particle setting as integer (0: All, 1: Minimal, 2: Decreased)
        mc.gameSettings.particleSetting = noParticles ? 1 : 2; // Adjust particle setting to "minimal"
        mc.gameSettings.saveOptions();
    }

    // Apply No HurtCam setting
    public static void applyNoHurtCam() {
        Minecraft mc = Minecraft.getMinecraft();
        
        // Skip the hurtcam effect by disabling the camera shake effect on the player.
        if (noHurtCam) {
            EntityPlayerSP player = mc.thePlayer;
            if (player != null) {
                // Reset the player view
                player.rotationPitch = 0.0F;
                player.rotationYaw = 0.0F;
            }
        }
    }
}
