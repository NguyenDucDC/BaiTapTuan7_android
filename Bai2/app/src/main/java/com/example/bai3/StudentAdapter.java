package com.example.bai3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<Student> studentList;
    private List<Student> studentListFull; // Danh sách đầy đủ để tìm kiếm

    public StudentAdapter(ArrayList<Student> studentList) {
        this.studentList = studentList;
        studentListFull = new ArrayList<>(studentList); // Sao chép danh sách ban đầu
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student currentStudent = studentList.get(position);
        holder.studentName.setText(currentStudent.getName());
        holder.studentId.setText(currentStudent.getStudentId());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void filter(String keyword) {
        if (keyword.isEmpty()) {
            studentList.clear();
            studentList.addAll(studentListFull);
        } else {
            ArrayList<Student> filteredList = new ArrayList<>();
            for (Student student : studentListFull) {
                if (student.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        student.getStudentId().contains(keyword)) {
                    filteredList.add(student);
                }
            }
            studentList.clear();
            studentList.addAll(filteredList);
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        TextView studentId;

        ViewHolder(View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            studentId = itemView.findViewById(R.id.student_id);
        }
    }
}


