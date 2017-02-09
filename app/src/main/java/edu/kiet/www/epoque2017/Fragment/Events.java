package edu.kiet.www.epoque2017.Fragment;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import edu.kiet.www.epoque2017.Adapters.EventAdapter;
import edu.kiet.www.epoque2017.CardObjects.EventCardData;
import edu.kiet.www.epoque2017.R;


public class Events extends Fragment {

    private FragmentActivity myContext;
    FragmentManager fragManager;
    private RecyclerView recyclerView;
    private EventAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);

        fragManager= myContext.getSupportFragmentManager();
        recyclerView=(RecyclerView)view.findViewById(R.id.EventRecyclerView);
        adapter=new EventAdapter(getContext(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public static List<EventCardData> getData(){
        List<EventCardData> data= new ArrayList<>();
        int eventImage=R.drawable.splash_background;
        String eventName="Event Name";
        for(int i=0;i<5;i++) {
            EventCardData current=new EventCardData();
            current.eventName=eventName;
            current.eventPhoto=eventImage;
            data.add(current);
        }
        return data;
    }


}
