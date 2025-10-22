# Package `exercise.UDP`

Package `exercise.UDP` chứa các bài tập thực hành sử dụng **giao thức UDP trong Java**. Các bài tập được chia thành 3 nhóm chính, mỗi nhóm nằm trong một package con, tương ứng với từng dạng dữ liệu hoặc chức năng xử lý.

---

## 1. `datatype`

- **Mục tiêu**: Làm quen với các thao tác xử lý dữ liệu dạng số (kiểu nguyên thủy hoặc danh sách số).
- **Đặc điểm**:
    - Dữ liệu vào/ra là các chuỗi biểu diễn danh sách số.
    - Sử dụng các phép toán cơ bản, tìm min/max, sắp xếp,...
    - **Ví dụ**: Tìm số lớn thứ hai và nhỏ thứ hai trong danh sách.

---

## 2. `string`

- **Mục tiêu**: Thao tác và xử lý chuỗi (xâu ký tự).
- **Đặc điểm**:
    - Dữ liệu là văn bản, câu, danh sách từ, email,...
    - Yêu cầu thực hiện các thao tác như chuẩn hóa, sắp xếp, lọc,...
    - **Ví dụ**: Sắp xếp danh sách từ không phân biệt chữ hoa/thường.

---

## 3. `object`

- **Mục tiêu**: Giao tiếp và xử lý **đối tượng Java** thông qua UDP.
- **Đặc điểm**:
    - Sử dụng `ObjectInputStream` và `ObjectOutputStream` để giải tuần tự và tuần tự hóa đối tượng.
    - Cần xử lý logic bên trong các class thực thể như `Book`, `Student`,...
    - Các **class entity được định nghĩa tại package `UDP`** (ngay trong thư mục `src`), **không nằm trong các package con của `exercise.UDP`**.
    - **Ví dụ**: Chuẩn hóa thông tin sách (`Book`) bao gồm tiêu đề, tên tác giả, ISBN, ngày xuất bản.

---

## So sánh 3 dạng bài

| Dạng bài     | Dữ liệu đầu vào | Phương pháp xử lý                    | Dữ liệu gửi lại         |
|--------------|------------------|--------------------------------------|--------------------------|
| `datatype`   | Chuỗi số         | Chuyển đổi, sắp xếp, tính toán      | Chuỗi kết quả            |
| `string`     | Văn bản          | Tách từ, chuẩn hóa, sắp xếp         | Chuỗi kết quả            |
| `object`     | Đối tượng        | Deserialize → xử lý → Serialize lại | Đối tượng đã chuẩn hóa   |

---

## Kỹ thuật sử dụng

- Các class bài tập kế thừa từ lớp `UDPBase`.
- Mỗi class override các phương thức:
    - `getPort()` – chỉ định cổng UDP.
    - `getCodeRequest()` – mã xác định bài tập.
    - `solve()` – xử lý logic chính.
- Kết quả được gửi lại qua socket UDP dưới dạng byte (string hoặc object tùy bài).

---
