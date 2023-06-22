package dev.rk.fragmentme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.rk.fragmentme.BookFragment;
import dev.rk.fragmentme.BookListFragment;
import dev.rk.fragmentme.R;
import dev.rk.fragmentme.models.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private BookListFragment.BookClickListener clickListener;

    public BookAdapter(List<Book> books, BookListFragment.BookClickListener clickListener) {
        this.books = books;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);

        holder.titleText.setText(book.title);
        holder.authorText.setText(book.author);

        holder.itemView.setOnClickListener((view -> {
            if(clickListener != null) clickListener.onClick(book);
        }));
    }

    @Override
    public int getItemCount() { return books.size(); }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView authorText;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titleText);
            authorText = itemView.findViewById(R.id.authorText);
        }
    }
}
