package com.example.dima.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dima on 09.03.2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContactsViewHolder> {

    private List<Contact> contacts;
    private Context context;

    public RVAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_cv, parent, false); // создаём вьюшку для кажого элемента
        return new ContactsViewHolder(view); //передаём вьюшку в качестве аргумента для холдера
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, int position) { //тут будет просходить обработка каждого элемента, кога он появится на экране
        final Contact contact = contacts.get(position);// получаем элемент для удобства использования

        holder.txtNum.setText(String.valueOf(contact.getPhone()));
        holder.txtName.setText(contact.getName());
        holder.txtDay.setText(String.valueOf(contact.getBirthday()));


        holder.cvListener.setRecord(contact);// как-то надо понимать с каким фильмом работаем
        holder.btnClickListener.setRecord(contact); // как-то надо понимать с фильмом  работаем

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    //это самый первый класс, который вы должны создать при содании адептера. В нём происходит инциализации всех View-элементов. Ага!
    class ContactsViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtNum, txtDay;
        Button btnRefactor;
        CardView cv;

        //Инициализируем слушатели
        CardViewClickListener cvListener = new CardViewClickListener();
        ButtonRemoveClickListener btnClickListener = new ButtonRemoveClickListener();

        ContactsViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.cvNameTextView);
            txtNum = itemView.findViewById(R.id.cvNumberTextView);
            txtDay = itemView.findViewById(R.id.cvBirthdayTextView);
            btnRefactor = itemView.findViewById(R.id.refactorButton);
            cv = itemView.findViewById(R.id.cv_rv);

            //цепляем слушатели
            cv.setOnClickListener(cvListener);
            btnRefactor.setOnClickListener(btnClickListener);


        }
    }

    //классы для обработки нажатий. Главное, чтобы они релизовывали интерфейс View.OnClickListener

    class CardViewClickListener implements View.OnClickListener {

        private Contact contact;

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ContactActivity.class);
            intent.putExtra("name", contact.getName());
            intent.putExtra("number", contact.getPhone());
            intent.putExtra("birthday", contact.getBirthday());
            intent.putExtra("id", String.valueOf(contact.getId()));
            ((Activity) context).startActivity(intent);
        }

        void setRecord(Contact contact) {
            this.contact = contact;
        }
    }

    class ButtonRemoveClickListener implements View.OnClickListener {
        Contact contact;

        @Override
        public void onClick(View v) {
            int position = contacts.indexOf(contact); // получаем индекс удаляемого элемента
            ContactsHelper ch = new ContactsHelper(context);
            ch.deleteContact(String.valueOf(contact.getId()));
            Log.d("test",ch.getAll().toString());


            contacts.remove(contact); // удаляем его из списка
            notifyItemRemoved(position); // метод для удалаении из самого RecyclerView. Именно он отвечает за анимации
        }

        void setRecord(Contact contact) {
            this.contact = contact;
        }
    }
}
