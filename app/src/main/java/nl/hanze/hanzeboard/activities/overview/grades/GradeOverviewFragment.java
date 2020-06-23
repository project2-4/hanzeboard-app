package nl.hanze.hanzeboard.activities.overview.grades;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.overview.OverviewActivity;

public class GradeOverviewFragment extends Fragment {

    private BarChart barChart;
    private TextView usersGradeView;
    private TextView passingView;
    private TextView averageGradeView;
    private TextView creditsView;

    private ArrayList<String> labels;
    private ArrayList<Integer> colorPanels;
    private ArrayList<String> labelsList;

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
        return inflater.inflate(R.layout.advanced_grade_overview, container, false);
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

        usersGradeView = view.findViewById(R.id.userGrade);
        passingView = view.findViewById(R.id.passingRate);
        averageGradeView = view.findViewById(R.id.averageGrade);
        creditsView = view.findViewById(R.id.credits);

        barChart = view.findViewById(R.id.barChart);
        barChart.setDescription(" ");
        barChart.getXAxis().setTextSize(11f);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisLeft().setAxisMinValue(0f);
        barChart.getAxisLeft().setTextSize(12);

        barChart.getAxisRight().setGranularity(1f);
        barChart.getAxisRight().setAxisMinValue(0f);
        barChart.getAxisRight().setTextSize(12);

        labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");

        colorPanels = new ArrayList<>();
        colorPanels.add(Color.rgb(73, 123, 133));
        colorPanels.add(Color.rgb(138, 231, 252));

        labelsList = new ArrayList<>();
        labelsList.add(getString(R.string.users_grade));
        labelsList.add(getString(R.string.grade_category));

        barChart.getLegend().setCustom(colorPanels, labelsList);
        barChart.getLegend().setTextSize(12);
        barChart.setScaleEnabled(false);
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
        OverviewActivity activity = (OverviewActivity) getActivity();
        Assignment assignment = activity.getAssignment();

        ArrayList<BarEntry> entries = new ArrayList<>();
        int[] colors = new int[10];
        int userGrade = (int) Math.floor(assignment.getGrade());
        double counter = 0;
        List<Integer> gradeDistribution = assignment.getGradeOverview();

        for(int i = 0; i < gradeDistribution.size(); i++){
            entries.add(new BarEntry(gradeDistribution.get(i), i));
            if(userGrade <= i){
                counter += gradeDistribution.get(i);
            }
            if(userGrade == i + 1){
                colors[i] = Color.rgb(73, 123, 133);
            }
            else{
                colors[i] = Color.rgb(138, 231, 252);
            }
        }
        int higherRate = (int) ((counter / assignment.getSubmissions()) * 100);
        int passingRate = (int) ((assignment.getPassed() / assignment.getSubmissions()) * 100);

        BarDataSet barDataset = new BarDataSet(entries, getString(R.string.grade_category));
        barDataset.setColors(colors);

        BarData data = new BarData(labels, barDataset);
        barChart.setData(data);
        barChart.getData().setHighlightEnabled(false);
        barChart.getBarData().setValueTextSize(0);

        String passedText = getString(R.string.passing_rate) + " " + passingRate + "% (" + (int) assignment.getPassed() + " " + getString(R.string.passing_text) + ")";
        String avgText = getString(R.string.average_grade) + " " + (Math.round(assignment.getAverageGrade() * 100.0) / 100.0);
        String creditsText = getString(R.string.credits) + " " + assignment.getCredits();
        String gradeText = getString(R.string.users_grade) + ": " + assignment.getGrade();
        if(counter == 0)
            gradeText += " (" + getString(R.string.user_excelled) + ")";
        else
            gradeText += " (" + higherRate + "% " + getString(R.string.other_users_excelled) + ")";

        passingView.setText(passedText);
        averageGradeView.setText(avgText);
        creditsView.setText(creditsText);
        usersGradeView.setText(gradeText);

        ((OverviewActivity) getActivity()).getSupportActionBar().setTitle(R.string.grade_overview);
        ((OverviewActivity) getActivity()).getSupportActionBar().setSubtitle(assignment.getAssignment());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((OverviewActivity) getActivity()).getSupportActionBar().setSubtitle("");
    }
}
