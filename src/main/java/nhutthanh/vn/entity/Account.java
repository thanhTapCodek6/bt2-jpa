package nhutthanh.vn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accounts")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
@NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username")
@NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AccountId")
	private int accountid;

	@Column(name = "Username", columnDefinition = "nvarchar(50) not null unique")
	@NotEmpty(message = "Không được phép rỗng")
	private String username;

	@Column(name = "Password", columnDefinition = "nvarchar(100) not null")
	@NotEmpty(message = "Không được phép rỗng")
	private String password;

	@Column(name = "Email", columnDefinition = "nvarchar(100) not null unique")
	@NotEmpty(message = "Không được phép rỗng")
	@Email(message = "Email không hợp lệ")
	private String email;

	@Column(name = "FullName", columnDefinition = "nvarchar(100) null")
	private String fullname;
	
	@Column(name = "Phone", columnDefinition = "varchar(20) null")
	private String phone;
	
	@Column(name = "Images", columnDefinition = "nvarchar(500) null")
	private String images;

	@Column(name = "Role", columnDefinition = "nvarchar(20) not null default 'USER'")
	private String role; // "ADMIN" hoặc "USER"

	@Column(name = "Active")
	private boolean active; // true = đã kích hoạt tài khoản

	// --- Dùng chung cho OTP (kích hoạt tài khoản & quên mật khẩu) ---
	@Column(name = "OtpCode", columnDefinition = "varchar(10) null")
	private String otpCode;

	@Column(name = "OtpExpiry")
	private LocalDateTime otpExpiry;
}