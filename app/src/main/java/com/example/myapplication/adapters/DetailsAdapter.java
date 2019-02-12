package com.example.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.Datum;
import com.example.myapplication.models.OrderProduct;
import com.example.myapplication.utils.CustomItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private Context mContext;
    private List<OrderProduct> orderProducts;
    private CustomItemClickListener customItemClickListener;
    private OrderProduct orderProduct;

    public DetailsAdapter(Context mContext, List<OrderProduct> orderProducts, CustomItemClickListener customItemClickListener) {
        this.mContext = mContext;
        this.orderProducts = orderProducts;
        this.customItemClickListener = customItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.detail_layout,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClickListener.onItemClick(v,viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        orderProduct= orderProducts.get(i);
        viewHolder.tvProductId.setText(""+orderProduct.getProductId());
        viewHolder.tvName.setText(orderProduct.getName());
        viewHolder.tvTotal.setText("Rs. "+orderProduct.getTotal());
        viewHolder.tvDeliveryDate.setText(orderProduct.getDeliveryDate());
        Picasso.get()
                .load(orderProduct.getImage())
                .into(viewHolder.productImage);
    }

    @Override
    public int getItemCount() {
        return orderProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvDeliveryDate,tvProductId,tvTotal;
        private ImageView productImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeliveryDate = itemView.findViewById(R.id.tv_delivery);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvProductId = itemView.findViewById(R.id.tv_product_id);
            tvTotal =itemView.findViewById(R.id.tv_total);
            productImage = itemView.findViewById(R.id.imageView);
        }
    }
}
