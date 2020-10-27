package worldofzuul;

public class Inventory {

    // attributes
    ArrayList<Item> items = new ArrayList<Item>();
    int MaxSizeInv;

    // constructors

    // methods
    // when buying things in the shop
    public void copyItem(Inventory invShop, Item itemShop) {
        if (this.items.size() != MaxSizeInv) // checks if inventory is not full

        {
            this.items.add(invShop, itemShop)
        }
    }

    // move item form Inventory inv 1 to inv 2
    // from player to room
    public void replaceItem(Inventory playerInv, Item playerItem Inventory roomInv) {
        if (this.items.size() != MaxSizeInv) // checks if inventory is not full

            //  Tjek et Item er i roomInv med samme ItemType som Item?
            // hvis nej, gør ikke mere, og skriv fejl!
            //
            // hvis JA
            //
            // find index nummer på pågældende item i roomInv
            //
            // int index:
            //
            // insæt player item i roomInv på index-plads.
            //
            // find index på item i playerInv
            //
            // slet item i playerInv på index vi lige har fundet.

/* SKITSE, SKAL GEEEEENNNEM TJEKKES!

        {
            item.getItemType() // item type of inv1

            int indexNr = inv2.items(inv1, item);
            // insert objekt
            inv2.items.set(item, indexNr);
            // delete object from inv 1
            //VIRKER DET?!
            inv1.items.remove(inv1.indexOf(item));

        }

 */
    }


}