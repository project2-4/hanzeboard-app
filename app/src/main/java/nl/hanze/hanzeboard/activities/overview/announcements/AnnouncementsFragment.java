package nl.hanze.hanzeboard.activities.overview.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import nl.hanze.hanzeboard.R;

public class AnnouncementsFragment extends Fragment {

    private AnnouncementsViewModel mViewModel;
    private RecyclerView announcementsView;
    private AnnouncementsAdapter announcementsAdapter;

    /**
     * Method to return a new instance of the AnnouncementsFragment class.
     *
     * @return a new instance of this class.
     */
    public static AnnouncementsFragment newInstance() {
        return new AnnouncementsFragment();
    }

    /**
     * Lifecycle method onCreateView. Sets the parameters and actions when the view is going to
     * be created.
     *
     * @param inflater used to 'inflate' the XML layout.
     * @param container the viewGroup which this fragment belongs to.
     * @param savedInstanceState used to pass data between activites.
     * @return a parsed/ 'inflated' view.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.announcements_fragment, container, false);
    }

    /**
     * Lifecycle method onViewCreated. Used to retrieve the view from the XML layout after the view
     * has been created.
     *
     * @param view the corresponding view of this class.
     * @param savedInstanceState a bundle used to pass data between activities.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        announcementsView = view.findViewById(R.id.announcementsView);
    }

    /**
     * Lifecycle method onActivityCreated. Used to bind the corresponding adapter to the retrieved
     * view after the activity has been created.
     *
     * @param savedInstanceState a bundle used to pass data between activities.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AnnouncementsViewModel.class);
        // TODO: Use the ViewModel

        Announcement[] announcementsArray = mViewModel.getAnnouncements();
        announcementsAdapter = new AnnouncementsAdapter(announcementsArray);
        announcementsView.setAdapter(announcementsAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        announcementsView.addItemDecoration(dividerItemDecoration);
    }

}
