package nhutthanh.vn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.createdDate DESC")
@NamedQuery(name = "Product.findLatest", query = "SELECT p FROM Product p WHERE p.status = 1 ORDER BY p.createdDate DESC")
@NamedQuery(name = "Product.countAll", query = "SELECT COUNT(p) FROM Product p WHERE p.status = 1")
@NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category.categoryid = :categoryId")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductId")
	private int productid;

	@Column(name = "ProductName", columnDefinition = "nvarchar(200) not null")
	@NotEmpty(message = "Không được phép rỗng")
	private String productname;

	@Column(name = "Price", columnDefinition = "double not null")
	@NotNull(message = "Không được phép rỗng")
	private Double price;

	@Column(name = "Description", columnDefinition = "nvarchar(2000) null")
	private String description;

	@Column(name = "Images", columnDefinition = "nvarchar(500) null")
	private String images;

	@Column(name = "Quantity", columnDefinition = "int not null default 0")
	private int quantity;

	@Column(name = "CreatedDate")
	private LocalDateTime createdDate;

	@Column(name = "Status", columnDefinition = "int not null default 1")
	private int status; // 1 = còn bán, 0 = ngừng bán

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CategoryId", nullable = false)
	private Category category;
}