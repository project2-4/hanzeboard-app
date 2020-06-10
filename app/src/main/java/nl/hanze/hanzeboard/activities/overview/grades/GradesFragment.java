package nl.hanze.hanzeboard.activities.overview.grades;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.api.responses.grade.GradeResponse;

public class GradesFragment extends Fragment {

    private GradesViewModel mViewModel;
    private RecyclerView gradesListView;

    /**
     * Method to return a new instance of the GradesFragment class.
     *
     * @return a new instance of this class.
     */
    public static GradesFragment newInstance() {
        return new GradesFragment();
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
        return inflater.inflate(R.layout.grades_fragment, container, false);
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
        gradesListView = view.findViewById(R.id.gradesListView);
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
        mViewModel = new ViewModelProvider(this).get(GradesViewModel.class);
        mViewModel.init(getContext());

        List<Grade> grades = new ArrayList<>();

        mViewModel.getGrades().observe(getViewLifecycleOwner(), gradeMessageResponse -> {
            List<GradeResponse> gradeMessageResponseList = gradeMessageResponse.getObjectList();
            StringBuilder temp = new StringBuilder();
            for(GradeResponse gradeResponse : gradeMessageResponseList){
                temp.append(gradeResponse.getAssignmentResponse().getName());
                temp.append(": ");
                temp.append(gradeResponse.getAssignmentResponse().getSubjectResponse().getName());
                grades.add(new Grade(Double.parseDouble(gradeResponse.getGrade()), temp.toString()));
                temp.setLength(0);
            }
            GradesAdapter gradesAdapter = new GradesAdapter(grades);
            gradesListView.setAdapter(gradesAdapter);
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        gradesListView.addItemDecoration(dividerItemDecoration);
    }

}
