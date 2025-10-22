# Mô tả tổng quát dự án


## 1. Tổng quan

Dự án được tổ chức theo cấu trúc thư mục và package nhằm quản lý rõ ràng các phần chức năng, giúp dễ dàng phát triển, bảo trì và mở rộng.

---

## 2. Cấu trúc thư mục chính

### 2.1 Package `checker`

* Chứa file main `SampleIO.java` (hoặc tương tự) dùng để người dùng tự viết, thử nghiệm và kiểm tra thuật toán nội bộ.
* Đây là môi trường test để phát triển thuật toán trước khi đưa vào code chuẩn gửi lên server.

### 2.2 Package `executor`

* Chứa các package con tương ứng với từng giao thức mạng phổ biến:

    * `executor.TCP`
    * `executor.UDP`
    * `executor.RMI`
    * `executor.WS` (viết tắt WebService)

* Mỗi package con gồm 2 thành phần chính:

    * **Base class**: lớp trừu tượng định nghĩa các phương thức chuẩn cho giao tiếp và xử lý bài toán theo giao thức.
    * **Runner class**: lớp chứa điểm vào (`main`), tạo kết nối, khởi tạo bài toán và điều phối luồng thực thi.

* Base và Runner sẽ có những khác biệt tùy theo đặc thù giao thức (ví dụ TCP dùng Socket, UDP dùng DatagramSocket).

---

### 2.3 Package `exercise`

* Chứa mã nguồn chi tiết cho từng bài tập, được phân chia theo từng chủ đề và giao thức.
* Mỗi bài tập viết theo chuẩn của giao thức tương ứng, kế thừa từ các lớp Base trong package `executor`.
* Đây là nơi cài đặt logic thuật toán cụ thể cho từng bài.

---

### 2.4 Các package `TCP`, `UDP`, `RMI`, `WS` nằm ngay trong thư mục `src`

* Chứa các lớp định nghĩa **entity** dùng cho các bài toán xử lý object theo từng giao thức.
* Hệ thống yêu cầu bắt buộc các class entity cho bài tập phải nằm trong các package này.
* Các entity này được sử dụng trong quá trình truyền nhận dữ liệu phức tạp (ví dụ serializable object, data model...).
* Đảm bảo tính tách biệt giữa lớp xử lý logic (`exercise`) và lớp định nghĩa dữ liệu (`TCP`, `UDP`, `RMI`, `WS`).

---

## 3. Tổng kết

| Package                   | Mục đích                                                      |
| ------------------------- | ------------------------------------------------------------- |
| `checker`                 | File test thử nghiệm thuật toán (nội bộ)                      |
| `executor`                | Quản lý giao tiếp mạng, chuẩn hóa xử lý theo từng giao thức   |
| `exercise`                | Cài đặt thuật toán bài tập theo từng giao thức                |
| `TCP`, `UDP`, `RMI`, `WS` | Định nghĩa entity cho các bài tập xử lý object theo giao thức |

---

> Dự án được thiết kế theo mô hình phân tầng, tách biệt rõ ràng các phần giao tiếp, xử lý và định nghĩa dữ liệu, giúp tăng tính module, dễ dàng bảo trì và nâng cấp.

## 4. Hướng dẫn phát triển

* Viết thuật toán và chạy thử nghiệm trong `checker.SampleIO`.
* Viết thuật toán trong một lớp (đặt trong package `exercice`) kế thừa lớp `xBase` (x là tên giao thức) trong package `executor`.
* Người dùng cho lớp `xClientRunner` (x là giao thức) trong package `excecutor.x` gọi đến lớp bài tập cụ thể.
* Chạy lớp `xClientRunner` và kiểm tra kết quả nhận được từ server thật trong console
* Khi hoàn thiện, zip các file `xClientRunner`, `xBase`, lớp xử lý riêng của bài tập.
