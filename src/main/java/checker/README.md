# SampleIO.java

## Mô tả

`SampleIO.java` là file main mẫu dùng để người dùng **tự viết, thử nghiệm và kiểm tra thuật toán** trước khi chuyển đổi và hoàn thiện code trong file chuẩn để gửi lên server.

### Mục đích

- Cho phép lập trình viên dễ dàng chạy thử các thuật toán trên dữ liệu mẫu.
- Giúp debug và kiểm tra kết quả thuật toán một cách nhanh chóng.
- Tránh lỗi khi nộp bài bằng cách đảm bảo thuật toán đã hoạt động đúng trên dữ liệu thử nghiệm.
- Tách riêng phần thử nghiệm với phần code chính thức để giữ cấu trúc dự án sạch sẽ và dễ bảo trì.

### Nội dung chính của file

- Đọc dữ liệu đầu vào mẫu (có thể từ console hoặc file test nội bộ).
- Thực hiện thuật toán hoặc các thao tác xử lý dữ liệu.
- In kết quả ra màn hình để người dùng kiểm tra.
- Có thể chứa các hàm tiện ích phục vụ cho việc test và debug.

### Lưu ý

- Đây không phải file gửi lên server mà chỉ dùng trong quá trình phát triển.
- Khi thuật toán đã ổn định, người dùng chuyển phần code đã test sang file chuẩn tương ứng (ví dụ: file class riêng biệt trong package excercise).
- Giữ cho file `SampleIO.java` đơn giản và dễ chỉnh sửa cho các lần thử nghiệm tiếp theo.

---

## Hướng dẫn sử dụng

1. Viết thuật toán trong `SampleIO.java` hoặc gọi hàm xử lý từ file khác.
2. Chạy chương trình và kiểm tra đầu ra với các bộ test do bạn cung cấp.
3. Khi kết quả đúng như mong muốn, sao chép phần xử lý logic sang file chính thức.
4. Xóa hoặc bỏ qua file `SampleIO.java` trong quá trình build hoặc nộp bài.

---

## Ví dụ đơn giản

```java
public class SampleIO {
    public static void main(String[] args) {
        // Ví dụ thuật toán đơn giản: tính tổng mảng
        int[] sampleData = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int num : sampleData) {
            sum += num;
        }
        System.out.println("Tổng của mảng là: " + sum);
    }
}
