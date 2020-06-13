package com.vasi.covidfinder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LocationListener locationListener;
    private LocationManager locationManager;

    private FirebaseDatabase database;

    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;
    private DatabaseReference databaseReference4;
    private DatabaseReference databaseReference5;


    private GoogleMap mMap;

    private static final LatLng JAFFNA = new LatLng(9.6626803, 80.019291);
    private static final LatLng ANURADHAPURA = new LatLng(8.3247705, 80.4117795);
    private static final LatLng POLANNARUVA = new LatLng(7.9432072, 81.0090043);
    private static final LatLng Welikanda = new LatLng(7.9481523, 81.2459815);
    private static final LatLng Batticaloa = new LatLng(7.7080851, 81.6905664);
    private static final LatLng Kandy = new LatLng(7.2876531, 80.6322971);
    private static final LatLng Kurunegala = new LatLng(7.4790517, 80.3592396);
    private static final LatLng Badulla = new LatLng(6.9918438, 81.0523019);
    private static final LatLng Monaragala = new LatLng(6.891202, 81.3426101);
    private static final LatLng Hambantota = new LatLng(6.1276257, 81.1221593);
    private static final LatLng Karapitiya = new LatLng(6.0670751, 80.2260293);
    private static final LatLng Ratnapura = new LatLng(6.6868086, 80.3930584);
    private static final LatLng Kalutara = new LatLng(6.5632673, 79.9852569);
    private static final LatLng Homagama = new LatLng(6.8472446, 79.9918054);
    private static final LatLng Kalubowila = new LatLng(6.8669092, 79.8769235);
    private static final LatLng NFTH = new LatLng(6.9245304, 79.9625788);
    private static final LatLng CEBH = new LatLng(6.9248911, 79.942781);
    private static final LatLng NIID = new LatLng(6.9224992, 79.918147);
    private static final LatLng CSHfW = new LatLng(6.9096618, 79.8848873);
    private static final LatLng LRH = new LatLng(6.917731, 79.8762224);
    private static final LatLng TNHoSL = new LatLng(6.9179405, 79.8667904);
    private static final LatLng DMH_T = new LatLng(6.9199973, 79.8705357);
    private static final LatLng Welisara = new LatLng(7.0249152, 79.9034545);
    private static final LatLng Ragama = new LatLng(7.0290902, 79.9240241);
    private static final LatLng Gampaha = new LatLng(7.0913515, 80.0006151);
    private static final LatLng Negombo = new LatLng(7.2119898, 79.8488362);
    private static final LatLng Matara = new LatLng(5.947364,80.5481391 );
    private static final LatLng KDU = new LatLng(6.825201,79.9070654 );
    private static final LatLng Chilaw = new LatLng(7.5723156,79.7971196);
    private static  final  LatLng Vavuniya = new LatLng(8.7604976,80.4998727 );

    private Marker mJaffna; //1
    private Marker mAnuradhapura;//2
    private Marker mPolannaruva;//3
    private Marker mWelikanda;//4
    private Marker mBatticaloa;//5
    private Marker mKandy;//6
    private Marker mKurunegala;//7
    private Marker mBadulla;//8
    private Marker mMonaragala;//9
    private Marker mHambantota;//10
    private Marker mKarapitiya;//11
    private Marker mRatnapura;//12
    private Marker mKalutara;//14
    private Marker mHomagama;//15
    private Marker mKalubowila;//16
    private Marker mNFTH;//17
    private Marker mCEBH;//18
    private Marker mNIID;//19
    private Marker mCSHfW;//20
    private Marker mLRH;//21
    private Marker mTNHoSL;//22
    private Marker mDMH_T;//23
    private Marker mWelisara;//24
    private Marker mRagama;//25
    private Marker mGambaha;//26
    private Marker mNegombo;//27
    private Marker mMatara;//28
    private Marker mKDU;//29
    private Marker mChilaw;//30
    private Marker mVavuniya;//31

    Button btn_confirmed;
    Button btn_deaths;
    Button btn_recovered;
    Button btn_suspected;
    Button btn_dateAndTime;
    Button centers;


    Button English;
    Button Tamil;
    Button Sinhala;

    Button E_confirmed;
    Button T_confirmed;
    Button S_confirmed;
    Button E_deaths;
    Button T_deaths;
    Button S_deaths;
    Button E_recovered;
    Button T_recovered;
    Button S_recovered;
    Button E_suspected;
    Button T_suspected;
    Button S_suspected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(MapsActivity.this,"Internet Connection Required",Toast.LENGTH_LONG)
                .show();
        setContentView(R.layout.activity_maps);

        btn_confirmed = (Button) findViewById(R.id.confirmed);
        btn_deaths = (Button) findViewById(R.id.deaths);
        btn_recovered = (Button) findViewById(R.id.recovered);
        btn_suspected = (Button) findViewById(R.id.suspected);
        btn_dateAndTime = (Button) findViewById(R.id.date_time);
        centers = (Button) findViewById(R.id.cntrs);

        English = (Button) findViewById(R.id.english);
        Sinhala = (Button) findViewById(R.id.sinhala);
        Tamil = (Button) findViewById(R.id.tamil);

        E_confirmed = (Button)  findViewById(R.id.english_confirmed);
        S_confirmed = (Button) findViewById(R.id.sinhala_confirm);
        T_confirmed = (Button) findViewById(R.id.tamil_confirm);

        E_deaths = (Button)  findViewById(R.id.english_death);
        S_deaths = (Button) findViewById(R.id.sinhala_death);
        T_deaths = (Button) findViewById(R.id.tamil_death);

        E_recovered = (Button)  findViewById(R.id.english_recovered);
        S_recovered = (Button) findViewById(R.id.sinhala_recovered);
        T_recovered = (Button) findViewById(R.id.tamil_recovered);

        E_suspected = (Button)  findViewById(R.id.english_suspected);
        S_suspected = (Button) findViewById(R.id.sinhala_suspected);
        T_suspected = (Button) findViewById(R.id.tamil_suspected);


        English.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                E_confirmed.setVisibility(View.VISIBLE);
                S_confirmed.setVisibility(View.INVISIBLE);
                T_confirmed.setVisibility(View.INVISIBLE);

                E_deaths.setVisibility(View.VISIBLE);
                S_deaths.setVisibility(View.INVISIBLE);
                T_deaths.setVisibility(View.INVISIBLE);

                E_recovered.setVisibility(View.VISIBLE);
                S_recovered.setVisibility(View.INVISIBLE);
                T_recovered.setVisibility(View.INVISIBLE);

                E_suspected.setVisibility(View.VISIBLE);
                S_suspected.setVisibility(View.INVISIBLE);
                T_suspected.setVisibility(View.INVISIBLE);



            }
        });

        Sinhala.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                E_confirmed.setVisibility(View.INVISIBLE);
                S_confirmed.setVisibility(View.VISIBLE);
                T_confirmed.setVisibility(View.INVISIBLE);

                E_deaths.setVisibility(View.INVISIBLE);
                S_deaths.setVisibility(View.VISIBLE);
                T_deaths.setVisibility(View.INVISIBLE);

                E_recovered.setVisibility(View.INVISIBLE);
                S_recovered.setVisibility(View.VISIBLE);
                T_recovered.setVisibility(View.INVISIBLE);

                E_suspected.setVisibility(View.INVISIBLE);
                S_suspected.setVisibility(View.VISIBLE);
                T_suspected.setVisibility(View.INVISIBLE);

            }
        });

        Tamil.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                E_confirmed.setVisibility(View.INVISIBLE);
                S_confirmed.setVisibility(View.INVISIBLE);
                T_confirmed.setVisibility(View.VISIBLE);

                E_deaths.setVisibility(View.INVISIBLE);
                S_deaths.setVisibility(View.INVISIBLE);
                T_deaths.setVisibility(View.VISIBLE);

                E_recovered.setVisibility(View.INVISIBLE);
                S_recovered.setVisibility(View.INVISIBLE);
                T_recovered.setVisibility(View.VISIBLE);

                E_suspected.setVisibility(View.INVISIBLE);
                S_suspected.setVisibility(View.INVISIBLE);
                T_suspected.setVisibility(View.VISIBLE);

            }
        });

        centers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyActivity.class);
                startActivity(intent);
            }
        });


        database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference("Confirmed");
        databaseReference2 =  database.getReference("Deaths");
        databaseReference3 = database.getReference("Recovered");
        databaseReference4 = database.getReference("Suspected");
        databaseReference5 = database.getReference("Date&Time");

        databaseReference1.setValue("000");
        databaseReference2.setValue("000");
        databaseReference3.setValue("000");
        databaseReference4.setValue("000");
        databaseReference5.setValue("000");


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.getValue(String.class);
                Context context;
                CharSequence text;
                btn_confirmed.setText(value1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value2 = dataSnapshot.getValue(String.class);
                Context context;
                CharSequence text;
                btn_deaths.setText(value2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value3 = dataSnapshot.getValue(String.class);
                Context context;
                CharSequence text;
                btn_recovered.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value4 = dataSnapshot.getValue(String.class);
                Context context;
                CharSequence text;
                btn_suspected.setText(value4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dateAndTime = dataSnapshot.getValue(String.class);
                Context context;
                CharSequence text;
                btn_dateAndTime.setText(dateAndTime);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        List <Marker>  markerList = new ArrayList<>();


        mJaffna = mMap.addMarker(new MarkerOptions()
                .position(JAFFNA)
                .title("Teaching Hospital, Jaffna"));
        mJaffna.setTag(0);
        markerList.add(mJaffna);


        mAnuradhapura = mMap.addMarker(new MarkerOptions()
                .position(ANURADHAPURA)
                .title("Teaching Hospital, Anuradhapura"));
        mAnuradhapura.setTag(0);
        markerList.add(mAnuradhapura);


        mPolannaruva = mMap.addMarker(new MarkerOptions()
                .position(POLANNARUVA)
                .title("Polonnaruwa General Hospital"));
        mPolannaruva.setTag(0);
        markerList.add(mPolannaruva);

        mWelikanda = mMap.addMarker(new MarkerOptions()
                .position(Welikanda)
                .title("Welikanda General Hospital"));
        mWelikanda.setTag(0);
        markerList.add(mWelikanda);

        mBatticaloa = mMap.addMarker(new MarkerOptions()
                .position(Batticaloa)
                .title("Teaching Hospital, Batticaloa மட்டகளப்பு போதனா வைத்தியசாலை"));
        mBatticaloa.setTag(0);
        markerList.add(mBatticaloa);

        mKandy = mMap.addMarker(new MarkerOptions()
                .position(Kandy)
                .title("Kandy National Hospital"));
        mKandy.setTag(0);
        markerList.add(mKandy);

        mKurunegala = mMap.addMarker(new MarkerOptions()
                .position(Kurunegala)
                .title("Teaching Hospital, Kurunegala"));
        mKurunegala.setTag(0);
        markerList.add(mKurunegala);

        mBadulla = mMap.addMarker(new MarkerOptions()
                .position(Badulla)
                .title("Provincial General Hospital, Badulla පළාත් මහ රෝහල - බදුල්ල"));
        mBadulla.setTag(0);
        markerList.add(mBadulla);

        mMonaragala = mMap.addMarker(new MarkerOptions()
                .position(Monaragala)
                .title("Base Hospital, Monaragala"));
        mMonaragala.setTag(0);
        markerList.add(mMonaragala);

        mHambantota = mMap.addMarker(new MarkerOptions()
                .position(Hambantota)
                .title("Hambantota General Hospital"));
        mHambantota.setTag(0);
        markerList.add(mHambantota);

        mKarapitiya = mMap.addMarker(new MarkerOptions()
                .position(Karapitiya)
                .title("Teaching Hospital, Karapitiya (කරාපිටිය ශික්ෂණ රෝහල)"));
        mKarapitiya.setTag(0);
        markerList.add(mKarapitiya);

        mRatnapura = mMap.addMarker(new MarkerOptions()
                .position(Ratnapura)
                .title("Teaching Hospital, Ratnapura"));
        mRatnapura.setTag(0);
        markerList.add(mRatnapura);

        mKalutara = mMap.addMarker(new MarkerOptions()
                .position(Kalutara)
                .title("General Hospital Nagoda, Kalutara"));
        mKalutara.setTag(0);
        markerList.add(mKalutara);

        mHomagama = mMap.addMarker(new MarkerOptions()
                .position(Homagama)
                .title("Base Hospita, Homagama"));
        mHomagama.setTag(0);
        markerList.add(mHomagama);


        mKalubowila = mMap.addMarker(new MarkerOptions()
                .position(Kalubowila)
                .title("Colombo South Teaching Hospital, Kalubowila"));
        mKalubowila.setTag(0);
        markerList.add(mKalubowila);

        mNFTH = mMap.addMarker(new MarkerOptions()
                .position(NFTH)
                .title("Dr. Neville Fernando Teaching Hospital"));
        mNFTH.setTag(0);
        markerList.add(mNFTH);

        mCEBH = mMap.addMarker(new MarkerOptions()
                .position(CEBH)
                .title("Colombo East Base Hospital (CEBH), Mulleriyawa"));
        mCEBH.setTag(0);
        markerList.add(mCEBH);

        mNIID = mMap.addMarker(new MarkerOptions()
                .position(NIID)
                .title("National Institute of Infectious Diseases"));
        mNIID.setTag(0);
        markerList.add(mNIID);

        mCSHfW = mMap.addMarker(new MarkerOptions()
                .position(CSHfW)
                .title("Castle Street Hospital for Women"));
        mCSHfW.setTag(0);
        markerList.add(mCSHfW);

        mLRH = mMap.addMarker(new MarkerOptions()
                .position(LRH)
                .title("Lady Ridgeway Hospital for Children (LRH)"));
        mLRH.setTag(0);
        markerList.add(mLRH);

        mTNHoSL = mMap.addMarker(new MarkerOptions()
                .position(TNHoSL)
                .title("The National Hospital of Sri Lanka"));
        mTNHoSL.setTag(0);
        markerList.add(mTNHoSL);

        mDMH_T = mMap.addMarker(new MarkerOptions()
                .position(DMH_T)
                .title("DeSoysa Maternity Hospital (Teaching)"));
        mDMH_T.setTag(0);
        markerList.add(mDMH_T);

        mWelisara = mMap.addMarker(new MarkerOptions()
                .position(Welisara)
                .title("National Hospital For Respiratory Diseases, Welisara"));
        mWelisara.setTag(0);
        markerList.add(mWelisara);

        mRagama = mMap.addMarker(new MarkerOptions()
                .position(Ragama)
                .title("Colombo North Teaching Hospital, Ragama"));
        mRagama.setTag(0);
        markerList.add(mRagama);

        mGambaha = mMap.addMarker(new MarkerOptions()
                .position(Gampaha)
                .title("District General Hospital Gampaha(දිස්ත්රික් මහා රෝහල ගම්පහ"));
        mGambaha.setTag(0);
        markerList.add(mGambaha);

        mNegombo = mMap.addMarker(new MarkerOptions()
                .position(Negombo)
                .title("District General Hospital, Negombo"));
        mNegombo.setTag(0);
        markerList.add(mNegombo);

        mMatara = mMap.addMarker(new MarkerOptions()
                .position(Matara)
                .title("District General Hospital, Matara"));
        mMatara.setTag(0);
        markerList.add(mMatara);

        mKDU = mMap.addMarker(new MarkerOptions()
                .position(KDU)
                .title("University Hospital KDU"));
        mKDU.setTag(0);
        markerList.add(mKDU);


        mChilaw = mMap.addMarker(new MarkerOptions()
                .position(Chilaw)
                .title("District General Hospital, Chilaw"));
        mChilaw.setTag(0);
        markerList.add(mChilaw);

        mVavuniya = mMap.addMarker(new MarkerOptions()
                .position(Vavuniya)
                .title("District General Hospital,Vavuniya"));
        mVavuniya.setTag(0);
        markerList.add(mVavuniya);


        for (Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,7));

        }






    }


}
