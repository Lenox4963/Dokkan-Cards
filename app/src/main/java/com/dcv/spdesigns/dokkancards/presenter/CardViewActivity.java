package com.dcv.spdesigns.dokkancards.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcv.spdesigns.dokkancards.R;
import com.dcv.spdesigns.dokkancards.model.main.CardInfoDatabase;
import com.dcv.spdesigns.dokkancards.model.glb.GlobalDataHolder;
import com.dcv.spdesigns.dokkancards.model.jp.JPDataHolder;

/**
 * Displays the selected card's details(sort version)
 */

public class CardViewActivity extends AppCompatActivity {

    // private static final String TAG = CardViewActivity.class.getSimpleName();

    //TODO:use the ButterKnife Library and remove the viewDefinitions() method to reduce clutter
    private ImageView cardArtImageView;
    private TextView leaderSkillDescText;
    private TextView superAttackTitleText;
    private TextView superAttackDescText;
    private TextView hpText;
    private TextView attText;
    private TextView defText;
    private TextView costText;
    private Button arrowButton;
    private int selectedItemPosition;
    private int identifier;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.cardview_refined);

        // Retrieving the data sent over from MainActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            selectedItemPosition = bundle.getInt("Card Index");
            identifier = bundle.getInt("Identifier");
        }

        // Initializing our views
        cardArtImageView = findViewById(R.id.cardArtImageView);
        viewDefinitions();
        setSelectedViewsInit();
        callInitDataMethods();

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullDetailsIntent = new Intent(CardViewActivity.this,CardFullDetailsActivity.class);
                fullDetailsIntent.putExtra("selectedPos",selectedItemPosition);
                fullDetailsIntent.putExtra("id",identifier);
                startActivity(fullDetailsIntent);
            }
        });
    }

    /**
     * Define the layout's views
     */
    private void viewDefinitions() {
        leaderSkillDescText = findViewById(R.id.leaderSkillDesc);
        superAttackTitleText = findViewById(R.id.superAttackTitle);
        superAttackDescText = findViewById(R.id.superAttackDesc);
        hpText = findViewById(R.id.HP);
        attText = findViewById(R.id.ATT);
        defText = findViewById(R.id.DEF);
        costText = findViewById(R.id.COST);
        arrowButton = findViewById(R.id.arrowButton);
    }

    /**
     * Sets the required textViews as selected to allow automatic scrolling
     */
    private void setSelectedViewsInit() {
        leaderSkillDescText.setSelected(true);
        superAttackTitleText.setSelected(true);
        superAttackDescText.setSelected(true);
    }

    /**
     * Calls the appropriate initCardView() method based on the identifier field's value
     */
    private void callInitDataMethods() {
        if(identifier == 0) {
            initCardViewMainScreen(selectedItemPosition);
        } else if(identifier == 1) {
            initCardViewGLB(selectedItemPosition);
        } else if(identifier == 2) {
            initCardViewJP(selectedItemPosition);
        } else {
            Log.d("DEBUG", "Error initializing card view data");
        }
    }

    /**
     * Initialize the cardViewActivity's views with the data from the CardInfoDatabase.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the MainScreen Fragment
     */
    private void initCardViewMainScreen(int selectedItemPosition) {
        cardArtImageView.setImageResource(CardInfoDatabase.cardArts[selectedItemPosition]);
        leaderSkillDescText.setText(CardInfoDatabase.leaderSkills[selectedItemPosition]);
        superAttackTitleText.setText(CardInfoDatabase.superAttacksName[selectedItemPosition]);
        superAttackDescText.setText(CardInfoDatabase.superAttacksDesc[selectedItemPosition]);
        hpText.setText(CardInfoDatabase.hp[selectedItemPosition].toString());
        attText.setText(CardInfoDatabase.att[selectedItemPosition].toString());
        defText.setText(CardInfoDatabase.def[selectedItemPosition].toString());
        costText.setText(CardInfoDatabase.cost[selectedItemPosition].toString());
    }

    /**
     * Initialize the cardViewActivity's views with the data from the GlobalDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the Global Fragment
     */
    private void initCardViewGLB(int selectedItemPosition) {
        cardArtImageView.setImageResource(GlobalDataHolder.cardArts.get(selectedItemPosition));
        leaderSkillDescText.setText(GlobalDataHolder.leaderSkills.get(selectedItemPosition));
        superAttackTitleText.setText(GlobalDataHolder.superAttacksName.get(selectedItemPosition));
        superAttackDescText.setText(GlobalDataHolder.superAttacksDesc.get(selectedItemPosition));
        hpText.setText(GlobalDataHolder.hp.get(selectedItemPosition).toString());
        attText.setText(GlobalDataHolder.att.get(selectedItemPosition).toString());
        defText.setText(GlobalDataHolder.def.get(selectedItemPosition).toString());
        costText.setText(GlobalDataHolder.cost.get(selectedItemPosition).toString());
    }

    /**
     * Initialize the cardViewActivity's views with the data from the JPDataHolder.java class
     * @param selectedItemPosition Used to initialize this activity's views if the intent was called from the JP Fragment
     */
    private void initCardViewJP(int selectedItemPosition) {
        cardArtImageView.setImageResource(JPDataHolder.cardArts.get(selectedItemPosition));
        leaderSkillDescText.setText(JPDataHolder.leaderSkills.get(selectedItemPosition));
        superAttackTitleText.setText(JPDataHolder.superAttacksName.get(selectedItemPosition));
        superAttackDescText.setText(JPDataHolder.superAttacksDesc.get(selectedItemPosition));
        hpText.setText(JPDataHolder.hp.get(selectedItemPosition).toString());
        attText.setText(JPDataHolder.att.get(selectedItemPosition).toString());
        defText.setText(JPDataHolder.def.get(selectedItemPosition).toString());
        costText.setText(JPDataHolder.cost.get(selectedItemPosition).toString());
    }
}
