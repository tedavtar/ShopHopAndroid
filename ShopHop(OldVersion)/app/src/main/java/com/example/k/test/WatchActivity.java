package com.example.k.test;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.qualcomm.toq.smartwatch.api.v1.deckofcards.Constants;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.DeckOfCardsEventListener;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.card.Card;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.card.ListCard;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.card.NotificationTextCard;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.card.SimpleTextCard;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.remote.DeckOfCardsManager;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.remote.RemoteDeckOfCards;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.remote.RemoteDeckOfCardsException;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.remote.RemoteResourceStore;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.remote.RemoteToqNotification;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.resource.CardImage;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.resource.DeckOfCardsLauncherIcon;
import com.qualcomm.toq.smartwatch.api.v1.deckofcards.util.ParcelableUtil;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import android.view.View;


public class WatchActivity extends Activity {

    private final static String PREFS_FILE= "prefs_file";
    private final static String DECK_OF_CARDS_KEY= "deck_of_cards_key";
    private final static String DECK_OF_CARDS_VERSION_KEY= "deck_of_cards_version_key";
    private DeckOfCardsManager mDeckOfCardsManager;
    private RemoteDeckOfCards mRemoteDeckOfCards;
    private RemoteResourceStore mRemoteResourceStore;
    private DeckOfCardsEventListener mDeckOfCardsEventListener;
    private ArrayList<CardImage> mCardImages;
    private ArrayList<Item> myList;
    private boolean foundDiscount = false;

    private ImageButton togos;

    View main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_watch);

        //togos = findViewById(R.id.togos);

        main = findViewById(R.id.targetstore);

        Bundle extras = getIntent().getExtras();
        myList = new ArrayList<Item>();
        ArrayList<String> itemNames = new ArrayList<String>();
        if (extras != null) {
            itemNames = extras.getStringArrayList("itemNames");
            int storeName = getIntent().getIntExtra("sc", 666);
            Log.d("STORE NAME", Integer.toString(storeName));
            switch (storeName){
                case 1:
                    //set background here
                    main.setBackground(getResources().getDrawable(R.drawable.berkeleybowl2));
                    break;
                case 2:
                    main.setBackground(getResources().getDrawable(R.drawable.savealot));
                    break;
                case 3:
                    //set background here
                    main.setBackground(getResources().getDrawable(R.drawable.traderjoes));
                    break;
                case 4:
                    main.setBackground(getResources().getDrawable(R.drawable.freshmarket));
                    break;
                case 5:
                    //set background here
                    main.setBackground(getResources().getDrawable(R.drawable.safeway));
                    break;
                default:
                    main.setBackground(getResources().getDrawable(R.drawable.ralphs));
                    break;



            }
        }
        for (String itemName : itemNames) {
            for (Item i: ItemssSingleton.get().getKnownItems()){
                if (i.getName().equalsIgnoreCase(itemName)){
                    myList.add(i);
                }
            }
            //String itemNameRecovered = dataRecieved.get(0);
            //Log.d(itemSuccessfullyRetrieved, "ERICPAULOS");
        }

        mDeckOfCardsManager = DeckOfCardsManager.getInstance(getApplicationContext());
        //install();
        init();
        //sendList();
        /*
        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Toast.makeText(getApplicationContext(),"Starting Discovery",Toast.LENGTH_LONG).show();

        final BroadcastReceiver mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //Toast.makeText(getApplicationContext(),"WE DID THE THING!!!!",Toast.LENGTH_LONG).show();

                String action = intent.getAction();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    if(device.getName() != null) {
                        if (device.getName().equalsIgnoreCase("NEIL")) {
                            foundDiscount = true;
                            sendDiscountNotification();
                            Toast.makeText(getApplicationContext(), "WE FOUND HIM", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        };

        //BroadcastReceiver adapterBroadcastReceiver = new BroadcastReceiver();
        final BroadcastReceiver adapterBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    if (foundDiscount == true){
                        Toast.makeText(getApplicationContext(), "Stopping discovery", Toast.LENGTH_LONG).show();
                        adapter.cancelDiscovery();
                        // unregisterReceiver(mReceiver);
                        //unregisterReceiver(adapterBroadcastReceiver);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "STARTING A NEW DISCOVERY", Toast.LENGTH_LONG).show();
                        adapter.startDiscovery();
                    }

                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(adapterBroadcastReceiver, filter2);
        adapter.startDiscovery();
        */



    }

    protected void onStart(){
        super.onStart();
        //install();
        Log.d(Constants.TAG, "ToqApiDemo.onStart");
        // If not connected, try to connect
        if (!mDeckOfCardsManager.isConnected()){
            try{
                mDeckOfCardsManager.connect();
            }
            catch (RemoteDeckOfCardsException e){
                e.printStackTrace();
            }
        }
        //
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);

        MenuItem settings = menu.findItem(R.id.PleaseShowUp);
        settings.setIcon(R.drawable.threelines);
        settings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //install();
            sendList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap getBitmap(String fileName) throws Exception{

        try{
            InputStream is= getAssets().open(fileName);
            return BitmapFactory.decodeStream(is);
        }
        catch (Exception e){
            throw new Exception("An error occurred getting the bitmap: " + fileName, e);
        }
    }

    private void init(){

        mDeckOfCardsEventListener = new DeckOfCardsEventListener() {
            @Override
            public void onCardOpen(String s) {

            }

            @Override
            public void onCardVisible(String s) {

            }

            @Override
            public void onCardInvisible(String s) {

            }

            @Override
            public void onCardClosed(String s) {
                ListCard listCard = mRemoteDeckOfCards.getListCard();
                Card card = listCard.get(s);
                listCard.remove(card);
                try {
                    mDeckOfCardsManager.updateDeckOfCards(mRemoteDeckOfCards, mRemoteResourceStore);
                } catch (RemoteDeckOfCardsException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed to delete Card from ListCard", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMenuOptionSelected(String s, String s2) {

            }

            @Override
            public void onMenuOptionSelected(String s, String s2, String s3) {

            }
        };

        mDeckOfCardsManager.addDeckOfCardsEventListener(mDeckOfCardsEventListener);

        // Create the resource store for icons and images
        mRemoteResourceStore= new RemoteResourceStore();

        DeckOfCardsLauncherIcon whiteIcon = null;
        DeckOfCardsLauncherIcon colorIcon = null;

        // Get the launcher icons
        try{
            whiteIcon= new DeckOfCardsLauncherIcon("white.launcher.icon", getBitmap("shopcart.png"), DeckOfCardsLauncherIcon.WHITE);
            colorIcon= new DeckOfCardsLauncherIcon("color.launcher.icon", getBitmap("shopcart.png"), DeckOfCardsLauncherIcon.COLOR);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Can't get launcher icon");
            return;
        }


        mCardImages = new ArrayList<CardImage>();
        try{
            for(Item item: myList){
                Bitmap bm = BitmapFactory.decodeResource(getResources(), item.getImagePath());
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bm, 250, 288, true); //replace this with actual image later
                mCardImages.add(new CardImage("card.image." + item.getName(), scaledBitmap));
            }

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(getBitmap("barcode.png"), 250, 288, true);
            mCardImages.add(new CardImage("card.image." + "barcode", scaledBitmap));
        }

        catch (Exception e){
            e.printStackTrace();
            System.out.println("Can't get picture icon");
            return;
        }

        for(CardImage image: mCardImages) {
            mRemoteResourceStore.addResource(image);
        }

        // Try to retrieve a stored deck of cards
        try {
            // If there is no stored deck of cards or it is unusable, then create new and store
            if ((mRemoteDeckOfCards = getStoredDeckOfCards()) == null){
                mRemoteDeckOfCards = createDeckOfCards();
                storeDeckOfCards();
            }
        }
        catch (Throwable th){
            th.printStackTrace();
            mRemoteDeckOfCards = null; // Reset to force recreate
        }

        // Make sure in usable state
        if (mRemoteDeckOfCards == null){
            mRemoteDeckOfCards = createDeckOfCards();
        }

        // Set the custom launcher icons, adding them to the resource store
        mRemoteDeckOfCards.setLauncherIcons(mRemoteResourceStore, new DeckOfCardsLauncherIcon[]{whiteIcon, colorIcon});

        // Re-populate the resource store with any card images being used by any of the cards
        for (Iterator<Card> it= mRemoteDeckOfCards.getListCard().iterator(); it.hasNext();){

            String cardImageId= ((SimpleTextCard)it.next()).getCardImageId();

            if ((cardImageId != null) && !mRemoteResourceStore.containsId(cardImageId)){

                //  if (cardImageId.equals("card.image.1")){
                //    mRemoteResourceStore.addResource(mCardImages[0]);
                // }

            }
        }
        //removeDeckOfCards();

    }

    private void sendDiscountNotification() {
        String[] message = new String[3];
        // requestId = random.nextInt(6);
        //message[0] = "Draw Request";
        //message[1] = names[requestId];
        // Create a NotificationTextCard
        message[0] = "Discount on Horizon Organic Milk";
        message[1] = "50% off!";
        message[2] = "Found in Aisle 6";
        NotificationTextCard notificationCard = new NotificationTextCard(System.currentTimeMillis(),
                "Discount on Milk", message);

        // Draw divider between lines of text
        notificationCard.setShowDivider(true);
        // Vibrate to alert user when showing the notification
        notificationCard.setVibeAlert(true);
        // Create a notification with the NotificationTextCard we made
        RemoteToqNotification notification = new RemoteToqNotification(this, notificationCard);

        try {
            // Send the notification
            mDeckOfCardsManager.sendNotification(notification);
            Toast.makeText(this, "Sent Notification", Toast.LENGTH_SHORT).show();
        } catch (RemoteDeckOfCardsException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send Notification", Toast.LENGTH_SHORT).show();
        }
    }


    private void install() {
        boolean isInstalled = true;

        try {
            isInstalled = mDeckOfCardsManager.isInstalled();
        }
        catch (RemoteDeckOfCardsException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: Can't determine if app is installed", Toast.LENGTH_SHORT).show();
        }

        if (!isInstalled) {
            try {
                mDeckOfCardsManager.installDeckOfCards(mRemoteDeckOfCards, mRemoteResourceStore);
            } catch (RemoteDeckOfCardsException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: Cannot install application", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "App is already installed!", Toast.LENGTH_SHORT).show();
        }

        try{
            storeDeckOfCards();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void uninstall() {
        boolean isInstalled = true;

        try {
            isInstalled = mDeckOfCardsManager.isInstalled();
        }
        catch (RemoteDeckOfCardsException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: Can't determine if app is installed", Toast.LENGTH_SHORT).show();
        }

        if (isInstalled) {
            try{
                mDeckOfCardsManager.uninstallDeckOfCards();
            }
            catch (RemoteDeckOfCardsException e){
                Toast.makeText(this, "Error uninstalling deck of cards", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "App already uninstalled", Toast.LENGTH_SHORT).show();
        }
    }


    private void sendList() {
        //remove all current cards
        removeDeckOfCards();
        ListCard listCard = mRemoteDeckOfCards.getListCard();
        int currSize = listCard.size();

        // Create a SimpleTextCard with 1 + the current number of SimpleTextCards
        int i = 0;
        for(Item item: myList){
            SimpleTextCard simpleTextCard = new SimpleTextCard(Integer.toString(currSize+1));
            simpleTextCard.setHeaderText(item.getName());
            simpleTextCard.setTitleText(item.getName());
            String[] messages = {item.getDesc()};
            simpleTextCard.setMessageText(messages);
            simpleTextCard.setReceivingEvents(true);
            simpleTextCard.setShowDivider(true);
            simpleTextCard.setCardImage(mRemoteResourceStore, mCardImages.get(i));
            listCard.add(simpleTextCard);
            currSize++;
            i++;

        }
        SimpleTextCard simpleTextCard = new SimpleTextCard(Integer.toString(currSize+1));
        simpleTextCard.setHeaderText("Barcode");
        simpleTextCard.setTitleText("Barcode");
        simpleTextCard.setShowDivider(true);
        simpleTextCard.setCardImage(mRemoteResourceStore, mCardImages.get(i));
        listCard.add(simpleTextCard);

        try {
            mDeckOfCardsManager.updateDeckOfCards(mRemoteDeckOfCards, mRemoteResourceStore);
        } catch (RemoteDeckOfCardsException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to Create SimpleTextCard", Toast.LENGTH_SHORT).show();
        }
    }

    private RemoteDeckOfCards getStoredDeckOfCards() throws Exception{

        if (!isValidDeckOfCards()){
            Log.w(Constants.TAG, "Stored deck of cards not valid for this version of the demo, recreating...");
            return null;
        }

        SharedPreferences prefs= getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        String deckOfCardsStr= prefs.getString(DECK_OF_CARDS_KEY, null);

        if (deckOfCardsStr == null){
            return null;
        }
        else{
            return ParcelableUtil.unmarshall(deckOfCardsStr, RemoteDeckOfCards.CREATOR);
        }

    }

    private void removeDeckOfCards() {
        // if(mRemoteDeckOfCards == null){
        mRemoteDeckOfCards = createDeckOfCards();
        // }
        ListCard listCard = mRemoteDeckOfCards.getListCard();
        while(listCard.size() != 0){
            listCard.remove(0);
        }

        try {
            mDeckOfCardsManager.updateDeckOfCards(mRemoteDeckOfCards, mRemoteResourceStore);
        } catch (RemoteDeckOfCardsException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to delete Card from ListCard", Toast.LENGTH_SHORT).show();
        }

    }

    private void storeDeckOfCards() throws Exception{
        // Retrieve and hold the contents of PREFS_FILE, or create one when you retrieve an editor (SharedPreferences.edit())
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        // Create new editor with preferences above
        SharedPreferences.Editor editor = prefs.edit();
        // Store an encoded string of the deck of cards with key DECK_OF_CARDS_KEY
        editor.putString(DECK_OF_CARDS_KEY, ParcelableUtil.marshall(mRemoteDeckOfCards));
        // Store the version code with key DECK_OF_CARDS_VERSION_KEY
        editor.putInt(DECK_OF_CARDS_VERSION_KEY, Constants.VERSION_CODE);
        // Commit these changes
        editor.commit();
    }

    // Check if the stored deck of cards is valid for this version of the demo
    private boolean isValidDeckOfCards(){

        SharedPreferences prefs= getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        // Return 0 if DECK_OF_CARDS_VERSION_KEY isn't found
        int deckOfCardsVersion= prefs.getInt(DECK_OF_CARDS_VERSION_KEY, 0);

        return deckOfCardsVersion >= Constants.VERSION_CODE;
    }

    // Create some cards with example content
    private RemoteDeckOfCards createDeckOfCards(){
        ListCard listCard= new ListCard();

        // SimpleTextCard simpleTextCard= new SimpleTextCard("card0");
        //listCard.add(simpleTextCard);

        // simpleTextCard= new SimpleTextCard("card1");
        // listCard.add(simpleTextCard);

        return new RemoteDeckOfCards(this, listCard);
    }


}
