package nl.hanze.hanzeboard.activities.overview.absent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nl.hanze.hanzeboard.R;

public class AbsenteesAdapter extends BaseAdapter {

    private Context context;
    private Absentee[] absentees;

    /**
     * Constructor for the AbsenteesAdapter class.
     *
     * @param context the context of the activity.
     * @param absentees the array of absentees.
     */
    public AbsenteesAdapter(Context context, Absentee[] absentees){
        this.context = context;
        this.absentees = absentees;
    }

    /**
     * Method to retrieve the number of items to be displayed.
     *
     * @return the number of items in the absentees array.
     */
    @Override
    public int getCount() {
        return absentees.length;
    }

    /**
     * Method to retrieve an Absentee object at position: position.
     *
     * @param position the index of the Absentee to be retrieved.
     * @return the corresponding Absentee object.
     */
    @Override
    public Object getItem(int position) {
        return absentees[position];
    }

    /**
     * Method to retrieve the id of an Absentee object.
     *
     * @param position the position and id of the object.
     * @return position/ id of the object.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method to return the view to be displayed.
     *
     * @param position the position/ index of the Absentee object in the absentees array.
     * @param convertView the view to be returned.
     * @param parent the parent of the view.
     * @return the view to be displayed.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }

        ImageView teacherImage = convertView.findViewById(R.id.teacherImage);
        TextView absentTeacher = convertView.findViewById(R.id.absentTeacher);
        TextView absenceReason = convertView.findViewById(R.id.absentReason);

        String teacherString = absentees[position].getName() + " (" + absentees[position].getAbbreviation() + ")";
        absentTeacher.setText(teacherString);
        absenceReason.setText(absentees[position].getStatus());

        return convertView;
    }
}
