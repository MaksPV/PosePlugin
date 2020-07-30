package ru.armagidon.poseplugin.api.utils.nms.item;

import net.minecraft.server.v1_16_R1.NBTBase;
import net.minecraft.server.v1_16_R1.NBTTagCompound;
import net.minecraft.server.v1_16_R1.NBTTagInt;
import net.minecraft.server.v1_16_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import ru.armagidon.poseplugin.api.utils.items.ItemUtil;

public class ItemUtil_v1_16_R1 extends ItemUtil
{

    public ItemUtil_v1_16_R1(ItemStack source) {
        super(source);
    }

    @Override
    public <T> void addTag(String name, T value) {

        net.minecraft.server.v1_16_R1.ItemStack stack = CraftItemStack.asNMSCopy(getSource());

        NBTTagCompound compound = stack.getOrCreateTag();

        NBTBase data = null;
        if(value.getClass().getSimpleName().equalsIgnoreCase("integer")){
            data = NBTTagInt.a((Integer) value);
        } else if(value.getClass().getSimpleName().equalsIgnoreCase("String")){
            data = NBTTagString.a((String) value);
        }
        compound.set(name, data);
        stack.setTag(compound);

        setSource(CraftItemStack.asBukkitCopy(stack));
    }

    @Override
    public boolean contains(String name) {
        net.minecraft.server.v1_16_R1.ItemStack stack = CraftItemStack.asNMSCopy(getSource());
        return stack.getTag()!=null&&stack.getTag().hasKey(name);
    }
}