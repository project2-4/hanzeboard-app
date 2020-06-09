package nl.hanze.hanzeboard.activities.overview.grades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.hanze.hanzeboard.R;

public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.ViewHolder> {

    private List<Grade> grades;

    /**
     * Constructor for the AnnouncementsAdapter class.
     *
     * @param grades the array of Announcement objects.
     */
    public GradesAdapter(List<Grade> grades){
        this.grades = grades;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new GradesAdapter.ViewHolder(view);
    }

    /**
     * Method to bind the data of a Grade object to the view's properties.
     *
     * @param holder the corresponding viewholder of the view.
     * @param position the position/ index of the Grade object in the grades array.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.assignmentListItem.setText(grades.get(position).getAssignment());
        holder.gradeListItem.setText(String.valueOf(grades.get(position).getGrade())) ;
    }

    /**
     * Method to retrieve the number of items to be displayed.
     *
     * @return the number of items in the grades array.
     */
    @Override
    public int getItemCount() {
        return grades.size();
    }

    /**
     * Inner class for the GradesAdapter. It stores the data necessary to display the view correctly.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView assignmentListItem;
        private TextView gradeListItem;

        /**
         * Constructor for this viewholder class.
         *
         * @param itemView the view which contains the view elements that are going to bound with
         *                 Grade data.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentListItem = itemView.findViewById(R.id.assignmentListItem);
            gradeListItem = itemView.findViewById(R.id.gradeListItem);
        }
    }
}
