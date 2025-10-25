package net.zusz.zcoffeecraft2.api.coffeebeantypes;


import net.minecraft.network.chat.MutableComponent;

public record CoffeeBeanType (
  String name,
  MutableComponent toolTip,
  Integer coffeeDurationModifier,
  Integer coffeeAmplifierModifier,
  Boolean hasSecondaryEffect,
  Integer secondaryEffectDurationModifier,
  Integer secondaryEffectAmplifierModifier
){}
