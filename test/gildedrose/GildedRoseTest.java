package gildedrose;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GildedRoseTest {

	@Parameters
	public static Collection<	Object[]> data() {
		Collection<Object[]> data = new ArrayList<Object[]>();

		data.addAll(Arrays.asList(new Object[][] { 
					{"0, At the end of each day our system lowers both quality and sell-in(in how many days) for every item",
						"Item with arbitrary name", 5, 49, 4, 48 } ,
					/*
					{"1, All items have a SellIn value which denotes the number of days we have to sell the item",
					"Item with arbitrary name", 5, 49, 4, 48}, //なぜ５が４になればテストに合格なのか???

					{"2, All items have a Quality value which denotes how valuable the item is",
					"Item with arbitrary name", 5, 49, 4, 48}, //これは一体何なのだ？？？
		
					{"3, At the end of each day our system lowers both values for every item",
					"Item with arbitrary name", 5, 49, 4, 48},
					*/
					
					//-----------------
					
					{"1, Once the sell by date has passed, Quality degrades twice as fast",
					"Item with arbitrary name", 0, 49, -1, 47}, 			
					
					{"2, The Quality of an item is never negative",
					"Item with arbitrary name", 1, 0, 0, 0}, 
	
					{"3,  \"Aged Brie\" actually increases in Quality the older it gets",
					"Aged Brie", 1 , 0, 0, 1},

					{"4, The Quality of an item is never more than 50",
					"Aged Brie", 1, 50, 0, 50},

					{"5,  \"Sulfuras\", being a legendary item, never has to be sold or decreases in Quality",
					"Sulfuras, Hand of Ragnaros", 1, 1, 1, 1},  //→untachable item. sell-in must not be changed.

					// \"Backstage passes\", like aged brie, increases in Quality as it's SellIn value approaches;
					// Quality increases by 2 when there are 10 days or less and  
					// by 3 when there are 5 days or less but Quality drops to 0 after the concert
					
					{"6,  \"Backstage passes\", Quality increases by 2 when there are 10 days or less",
					"Backstage passes to a TAFKAL80ETC concert", 11, 1, 10, 2},	
					{"7,  \"Backstage passes\", Quality increases by 2 when there are 10 days or less",
						"Backstage passes to a TAFKAL80ETC concert", 10, 1, 9, 3},
				
					
					{"8,  \"Backstage passes\", Quality increases by 3 when there are 5 days or less but Quality drops to 0 after the concert",
					"Backstage passes to a TAFKAL80ETC concert", 6, 1, 5, 3},
					{"9,  \"Backstage passes\", Quality increases by 3 when there are 5 days or less but Quality drops to 0 after the concert",
					"Backstage passes to a TAFKAL80ETC concert", 5, 1, 4, 4},

					
					{"10,  \"Backstage passes\", Quality drops to 0 after the concert",
					"Backstage passes to a TAFKAL80ETC concert", 1, 49, 0, 50},
					{"11,  \"Backstage passes\", Quality drops to 0 after the concert",
					"Backstage passes to a TAFKAL80ETC concert", 0, 49, -1, 0},

					{"12,  \"Backstage passes\", Quality increases by 2 when there are 10 days or less",
						"Backstage passes to a TAFKAL80ETC concert", 10, 49, 9, 50}, //The Quality of an item is never more than 50

					
					{"13,  some test", // no special treatment
					"Conjured Mana Cake", 1, 48, 0, 47},
					
					{"14,  some test", // no special treatment
					"Elixir of the Mongoose", 1, 48, 0, 47},
					
					{"15, ", // no special treatment
					"+5 Dexterity Vest", 1, 48, 0, 47},
					
					
					{"16,  For Aged Brie, mark 01 else. steht nicht in der Spezifikation",
					"Aged Brie", 0, 46, -1, 48}, //see 14. not only for Aged Brie but for every item
					
					{"17,  mark02. The Quality of an item is never more than 50",
					"Aged Brie\"", 0, 50, -1, 48}, // but it is already 50, it will be 48. I don't know why. This is the specification.
					
					{"18,  any item which passeed sell in date",
					"Item with arbitrary name", 0, 48, -1, 46},
					
					{"19,  mark04. ",
					"Sulfuras, Hand of Ragnaros", -1, 49, -1, 49},
					//"Item with arbitrary name", 0, 49, -1, 49},
												
					{"20,  mark03. I don't know what is this for.",
					"some item", 0, 0, -1, 0},
					
					//Method must be implemented in GildedRose.class
					/*
					{" \"Conjured\" items degrade in Quality twice as fast as normal items",
					"Item with arbitrary name", 5, 49, 4, 48},
					*/					

				}));
				return data;
	}
	

	String message;
	String itemName;
	int sellIn;
	int quality;
	int expectedSellIn;
	int expectedQuality;
	Item item;

	
	public GildedRoseTest(String message, String itemName, int sellIn,
			int quality, int expectedSellIn, int expectedQuality) {
		this.message = message;
		this.itemName = itemName;
		this.sellIn = sellIn;
		this.quality = quality;
		this.expectedSellIn = expectedSellIn;
		this.expectedQuality = expectedQuality;
	}
	

	@Before
	public void setUp() {
		List<Item> items = new ArrayList<Item>();
		items.add(item = new Item(itemName, sellIn, quality));
		GildedRose.setItems(items);
	}

	
	@Test
	public void testQualityUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " Quality ", expectedQuality, item.getQuality());
	}

	
	@Test
	public void testSellInUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " SellIn", expectedSellIn, item.getSellIn());
	}
	
}
