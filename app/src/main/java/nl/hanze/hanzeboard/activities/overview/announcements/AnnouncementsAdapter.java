package nl.hanze.hanzeboard.activities.overview.announcements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import nl.hanze.hanzeboard.R;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.ViewHolder> {

    private List<Announcement> announcements;

    /**
     * Constructor for the AnnouncementsAdapter class.
     *
     * @param announcements the array of Announcement objects, announcements.
     */
    public AnnouncementsAdapter(List<Announcement> announcements){
        this.announcements = announcements;
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
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Method to bind the data of a Announcement object to the view's properties.
     *
     * @param holder the corresponding viewholder of the view.
     * @param position the position/ index of the Announcement object in the announcements array.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.announcerView.setText(announcements.get(position).getAnnouncer());
        holder.announcementTitle.setText(announcements.get(position).getTitle());
        holder.contentView.setText(announcements.get(position).getContent());
        holder.dateView.setText(announcements.get(position).getDate());
        holder.expandableLayout.setVisibility(announcements.get(position).isExpanded() ? View.VISIBLE : View.GONE);
    }

    /**
     * Method to retrieve the number of items to be displayed.
     *
     * @return the number of items in the announcements array.
     */
    @Override
    public int getItemCount() {
        return announcements.size();
    }

    /**
     * Inner class for the AnnouncementsAdapter. It stores the data necessary to display the view correctly.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView announcementTitle;
        private TextView announcerView;
        private TextView contentView;
        private TextView dateView;

        private ConstraintLayout expandableLayout;

        /**
         * Constructor for this viewholder class.
         *
         * @param itemView the view which contains the view elements that are going to bound with
         *                 Announcement data.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            announcementTitle = itemView.findViewById(R.id.announcementTitle);
            announcerView = itemView.findViewById(R.id.announcerView);
            contentView = itemView.findViewById(R.id.contentView);
            dateView = itemView.findViewById(R.id.dateView);

            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            itemView.setOnClickListener(v -> {
                Announcement announcement = announcements.get(getAdapterPosition());
                announcement.setExpanded(!announcement.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
