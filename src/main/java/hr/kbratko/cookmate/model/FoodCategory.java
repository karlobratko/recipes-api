package hr.kbratko.cookmate.model;

import org.springframework.util.StringUtils;

public enum FoodCategory {

  VEGETABLES,
  FRUITS,
  GRAINS,
  PROTEIN,
  DAIRY,
  EGGS,
  WATER,
  CONFECTIONS;

  @Override
  public String toString() {
    return StringUtils.capitalize(name());
  }

}
