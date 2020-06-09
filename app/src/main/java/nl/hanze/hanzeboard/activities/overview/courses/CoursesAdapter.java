package nl.hanze.hanzeboard.activities.overview.courses;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.hanze.hanzeboard.R;
import nl.hanze.hanzeboard.activities.overview.OverviewActivity;
import nl.hanze.hanzeboard.api.responses.course.CourseResponse;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private List<CourseResponse> courses;

    /**
     * Constructor for the CourseAdapter class.
     *
     * @param courses the array of CourseResponse objects.
     */
    public CoursesAdapter(List<CourseResponse> courses){
        this.courses = courses;
    }

    /**
     * Lifecycle method onCreateViewHolder. It 'inflates'/ takes the layout XML and parses it
     * to create the view and viewgroup objects from the elements and their attributes. Then adding
     * those to the hierarchy of those views and viewgroups to the parent ViewGroup.
     *
     * @param parent the parent which the view belongs to.
     * @param viewType the type of view.
     * @return the ViewHolder which stores data needed to display the view.
     */
    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.resource_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Method to bind the data of a CourseResponse object to the view's properties.
     *
     * @param holder the corresponding viewholder of the view.
     * @param position the position/ index of the CourseResponse object in the courses array.
     */
    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        holder.courseTitle.setText(courses.get(position).getName());
    }

    /**
     * Method to retrieve the number of items to be displayed.
     *
     * @return the number of items in the courses array.
     */
    @Override
    public int getItemCount() {
        return courses.size();
    }

    /**
     * Inner class for the CourseAdapter. It stores the data necessary to display the view correctly.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTitle;

        /**
         * Constructor for this viewholder class.
         *
         * @param itemView the view which contains the view elements that are going to bound with
         *                 Announcement data.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitle);

            itemView.setOnClickListener(v -> {
                OverviewActivity host = (OverviewActivity) v.getContext();
                host.setCurrentCourse(getAdapterPosition());
                NavHostFragment.findNavController(FragmentManager.findFragment(v)).navigate(R.id.navigate_to_announcements);
            });
        }
    }
}
