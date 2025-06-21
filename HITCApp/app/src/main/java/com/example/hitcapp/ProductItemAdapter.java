package com.example.hitcapp;// package com.example.hitcapp;
// ProductItemAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductViewHolder> implements Filterable {

    private List<ProductItem> productList;
    private List<ProductItem> productListFull; // Danh sách đầy đủ để lọc
    private OnProductActionListener listener;
    private Context context; // Giữ context nếu cần

    // Interface để gửi sự kiện click ra ngoài Activity
    public interface OnProductActionListener {
        void onBuyClick(ProductItem product);
        void onDetailsClick(ProductItem product);
    }

    // Constructor mới
    public ProductItemAdapter(Context context, List<ProductItem> productList, OnProductActionListener listener) {
        this.context = context;
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList); // Sao chép để lọc
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Đảm bảo inflate đúng file layout item sản phẩm của bạn
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItem product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice()); // Gán giá
        holder.productImage.setImageResource(product.getImageResId());

        // Gán Listener cho các nút
        holder.buyButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBuyClick(product);
            }
        });

        holder.detailsButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDetailsClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ProductItem item : productListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        Button buyButton;
        Button detailsButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ các View từ item_product.xml
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            buyButton = itemView.findViewById(R.id.buy_button);
            detailsButton = itemView.findViewById(R.id.details_button);
        }
    }
}