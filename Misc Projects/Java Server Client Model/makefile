all:
	javac store/OnlineStore.java store/StoreImpl.java store/StoreItem.java store/StoreUser.java store/StoreAdmin.java store/StoreCustomer.java common/StoreInterface.java browser/StoreBrowser.java browser/commands/CommandFactory.java browser/commands/CommandInterface.java browser/commands/AddAdmin.java browser/commands/AddCustomer.java browser/commands/AddItem.java browser/commands/AddToCart.java browser/commands/ClearShoppingCart.java browser/commands/CustomerCheckOut.java browser/commands/DeleteUser.java browser/commands/ItemDelete.java browser/commands/PrintAllItems.java browser/commands/PrintAllUsers.java browser/commands/UpdateItemPrice.java browser/commands/UpdateItemQty.java
runserver:
	java store/OnlineStore
runclient:
	java browser/StoreBrowser
startreg:
	rmiregistry 9674 &