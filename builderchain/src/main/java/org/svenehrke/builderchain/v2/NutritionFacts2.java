/*
 * Copyright (c) 2010 by Sven Ehrke
 */

package org.svenehrke.builderchain.v2;

/**
 * Similar to NutritionFacts1 but using interfaces (IServings, IOptionsBuilder)
 */
class NutritionFacts2 {

	private final Data data;

	private static class Data {
		private int servingSize; // required
		private int servings; // required
		private int calories; // optional
		private int fat; // optional
		private int sodium; // optional
		private int carbohydrate; // optional

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Data data = (Data) o;

			if (calories != data.calories) return false;
			if (carbohydrate != data.carbohydrate) return false;
			if (fat != data.fat) return false;
			if (servingSize != data.servingSize) return false;
			if (servings != data.servings) return false;
			if (sodium != data.sodium) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = servingSize;
			result = 31 * result + servings;
			result = 31 * result + calories;
			result = 31 * result + fat;
			result = 31 * result + sodium;
			result = 31 * result + carbohydrate;
			return result;
		}

		@Override
		public String toString() {
			return "Data{" +
				"servingSize=" + servingSize +
				", servings=" + servings +
				", calories=" + calories +
				", fat=" + fat +
				", sodium=" + sodium +
				", carbohydrate=" + carbohydrate +
				'}';
		}
	}

	public NutritionFacts2(FinalBuilder aBuilder) {
		data = aBuilder.data;
	}

	// ------------------------
	public static IServings withServingSize(int aServingSize) {
		Data d = new Data();
		d.servingSize = aServingSize;
		return new B1(d, new FinalBuilder(d));
	}

	public static class B1 implements IServings {
		final private Data data;
		final FinalBuilder nb;
		private B1(final Data aData, final FinalBuilder aNextBuilder) {
			data = aData;
			nb = aNextBuilder;
		}
		public IOptionsBuilder withServings(int aValue) {
			nb.data.servings = aValue;
			return nb;
		}
		protected Data getData() {
			return nb.data;
		}
	}

	public static class FinalBuilder implements IOptionsBuilder {
		private Data data;
		private FinalBuilder(Data aData) {
			data = aData;
		}

		public FinalBuilder andCalories(final int aCalories) {
			data.calories = aCalories;
			return this;
		}
		public FinalBuilder andFat(final int aFat) {
			data.fat = aFat;
			return this;
		}
		public FinalBuilder andSodium(final int aSodium) {
			data.sodium = aSodium;
			return this;
		}
		public FinalBuilder andCarbohydrate(final int aCarbohydrate) {
			data.carbohydrate = aCarbohydrate;
			return this;
		}

		public NutritionFacts2 build() {
			return new NutritionFacts2(this);
		}
	}

	public int getServingsSize() {
		return data.servingSize;
	}
	public int getServings() {
		return data.servings;
	}
	public int getCalories() {
		return data.calories;
	}
	public int getFat() {
		return data.fat;
	}
	public int getSodium() {
		return data.sodium;
	}
	public int getCarbohydrate() {
		return data.carbohydrate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NutritionFacts2 that = (NutritionFacts2) o;

		if (data != null ? !data.equals(that.data) : that.data != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return data != null ? data.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "NutritionFacts2{" +
			"data=" + data +
			'}';
	}

	public static interface IServings {
		public IOptionsBuilder withServings(int value);
	}

	public static interface IOptionsBuilder {
		public IOptionsBuilder andCalories(int value);
		public IOptionsBuilder andFat(int value);
		public IOptionsBuilder andSodium(int value);
		public IOptionsBuilder andCarbohydrate(int value);
		public NutritionFacts2 build();
	}
}