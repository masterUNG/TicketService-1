package korntip.skyict.co.th.ticketservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import korntip.skyict.co.th.ticketservice.R;
import korntip.skyict.co.th.ticketservice.utility.MyConstance;
import korntip.skyict.co.th.ticketservice.utility.ReadAllData;
import korntip.skyict.co.th.ticketservice.utility.ShowListTicketAdapter;

public class NewItemFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();


    }   // Main Method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewNewItem);

        try {

            MyConstance myConstance = new MyConstance();
            String urlJSON = myConstance.getUlrNewIemString();

            ReadAllData readAllData = new ReadAllData(getContext());
            readAllData.execute(urlJSON);
            String jsonString = readAllData.get();
            Log.d("30MayV1", "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] docNoStrings = new String[jsonArray.length()];
            String[] serialStrings = new String[jsonArray.length()];
            String[] detailStrings = new String[jsonArray.length()];
            String[] severityStrings = new String[jsonArray.length()];
            String[] assignStrings = new String[jsonArray.length()];
            String[] statusStrings = new String[jsonArray.length()];
            String[] dueDateStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                docNoStrings[i] = jsonObject.getString("DocNo");
                serialStrings[i] = jsonObject.getString("SerialNumber");
                detailStrings[i] = jsonObject.getString("Detail");
                severityStrings[i] = jsonObject.getString("Severity");
                assignStrings[i] = jsonObject.getString("Assignee");
                statusStrings[i] = jsonObject.getString("Status");
                dueDateStrings[i] = jsonObject.getString("DueDate");

            }   // for

            ShowListTicketAdapter showListTicketAdapter = new ShowListTicketAdapter(getContext(),
                    docNoStrings, serialStrings, detailStrings, severityStrings, assignStrings, statusStrings, dueDateStrings);
            listView.setAdapter(showListTicketAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newitem, container, false);
        return view;
    }
}
