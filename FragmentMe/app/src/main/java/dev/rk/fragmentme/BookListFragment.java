package dev.rk.fragmentme;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.rk.fragmentme.adapters.BookAdapter;
import dev.rk.fragmentme.models.Book;

public class BookListFragment extends Fragment {

    private BookClickListener clickListener;
    private RecyclerView bookList;
    private BookAdapter adapter;

    public interface BookClickListener {
        void onClick(Book book);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        bookList = view.findViewById(R.id.bookList);
        bookList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(testList(), clickListener);
        bookList.setAdapter(adapter);
        return view;
    }

    private List<Book> testList() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("tcrst", "11111111111"));
        books.add(new Book("kz", "8888"));
        books.add(new Book("hk", "96796"));
        books.add(new Book("ypk", "00000"));
        return books;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BookClickListener) {
            clickListener = (BookClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}