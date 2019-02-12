package com.example.myapplication.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.Datum;
import com.example.myapplication.models.OrderProduct;
import com.example.myapplication.utils.CustomItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private Context mContext;
    private List<Datum> datumList;
    private Datum datum;
    private DetailsAdapter detailsAdapter;
    private List<OrderProduct> orderProducts;
    private Dialog dialog;
    private TextView tvproductID,tvQty,tvName,tvBrand,tvSize,tvcolor,tvPRice,tvDiscount,tvTotal,tvDelivery;
    ImageView imageView;

    public OrderDetailsAdapter(Context mContext,List<Datum> datumList) {
        this.mContext = mContext;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.order_main_layout,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        datum = datumList.get(i);
        orderProducts = datum.getOrderProducts();
    viewHolder.tvOrderDate.setText(datum.getOrderDate());
    viewHolder.tvOrderNo.setText("#"+datum.getId()+"");
    viewHolder.tvStatus.setText(datum.getOrderStatus());
    viewHolder.recyclerView.setHasFixedSize(true);
    viewHolder.recyclerView.setNestedScrollingEnabled(false);
    viewHolder.tvSubtotal.setText("Rs. "+datum.getSubtotal());
    viewHolder.tvGrandTotal.setText("Rs. "+datum.getGrandtotal());
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        DetailsAdapter detailsAdapter= new DetailsAdapter(mContext, orderProducts, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                dialog = new Dialog(mContext, R.style.DialogTheme);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.product_detail_layout);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                tvName =dialog.findViewById(R.id.tv_product_name);
                tvBrand =dialog.findViewById(R.id.tv_brand_name);
                tvcolor =dialog.findViewById(R.id.tv_color);
                tvQty =dialog.findViewById(R.id.tv_quantity);
                tvSize =dialog.findViewById(R.id.tv_size);
                tvPRice =dialog.findViewById(R.id.tv_price);
                tvDiscount =dialog.findViewById(R.id.tv_discount);
                tvTotal =dialog.findViewById(R.id.tv_total);
                tvproductID =dialog.findViewById(R.id.tv_product_id);
                tvDelivery =dialog.findViewById(R.id.tv_delivery);
                imageView =dialog.findViewById(R.id.imageView);

                tvName.setText(orderProducts.get(position).getName());
                tvBrand.setText(orderProducts.get(position).getBrand());
                tvcolor.setText(orderProducts.get(position).getColor());
                tvQty.setText(""+orderProducts.get(position).getQty());
                tvSize.setText(orderProducts.get(position).getSize());
                tvPRice.setText("Rs. "+orderProducts.get(position).getPrice());
                tvDiscount.setText("Rs. "+orderProducts.get(position).getDiscount());
                tvTotal.setText("Rs. "+orderProducts.get(position).getTotal());
                tvDelivery.setText(orderProducts.get(position).getDeliveryDate());
                tvproductID.setText(orderProducts.get(position).getProductId()+"");
                Picasso
                        .get()
                        .load(orderProducts.get(position).getImage())
                        .into(imageView);



            }
        });
        viewHolder.recyclerView.setAdapter(detailsAdapter);
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderDate,tvStatus,tvOrderNo,tvSubtotal,tvGrandTotal;
        private RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvOrderNo = itemView.findViewById(R.id.tv_order_id);
            tvStatus = itemView.findViewById(R.id.tv_status);
            recyclerView = itemView.findViewById(R.id.rv_details);
            tvSubtotal = itemView.findViewById(R.id.tv_subtotals);
            tvGrandTotal = itemView.findViewById(R.id.tv_grandtotal);
        }
    }
}
