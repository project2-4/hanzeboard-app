package nl.hanze.hanzeboard.activities.overview.courses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.overview.OverviewActivity;
import nl.hanze.hanzeboard.activities.overview.announcements.AnnouncementsFragment;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;

public class CoursesFragment extends Fragment {

    private RecyclerView coursesView;

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
        return inflater.inflate(R.layout.courses_fragment, container, false);
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
        coursesView = view.findViewById(R.id.coursesView);
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

        List<CourseResponse> courses = ((OverviewActivity) requireActivity()).getCourseList();
        Log.v("COURSES SIZE: ", String.valueOf(courses.size()));

        coursesView.setAdapter(new CoursesAdapter(courses));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        coursesView.addItemDecoration(dividerItemDecoration);
    }

}
