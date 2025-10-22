# Package `exercise.TCP`

Package `exercise.TCP` chứa các bài tập thực hành về lập trình mạng (TCP) trong Java, được chia thành 4 nhóm nhỏ theo kiểu xử lý luồng dữ liệu:

---

## 1. `bytestream` – Làm việc với `InputStream` / `OutputStream`

- **Lớp sử dụng chính**: `InputStream`, `OutputStream`
- **Đặc điểm**:
    - Đọc và ghi dữ liệu thô ở dạng byte.
    - Cần xử lý dữ liệu thủ công, ví dụ: đọc vào buffer, chuyển thành chuỗi, tách dữ liệu,...
    - Phù hợp khi dữ liệu không có định dạng cụ thể (text, số,... đều ở dạng byte).
- **Cách đọc/ghi**:
    - Dùng mảng byte (`byte[]`) để đọc và chuyển đổi dữ liệu.

---

## 2. `characterstream` – Làm việc với `BufferedReader` / `BufferedWriter`

- **Lớp sử dụng chính**: `BufferedReader`, `BufferedWriter`
- **Đặc điểm**:
    - Xử lý dữ liệu văn bản (text) theo dòng hoặc từng ký tự.
    - Đơn giản hơn `bytestream` khi xử lý chuỗi.
    - Có hỗ trợ đọc dòng (`readLine()`) và ghi dòng (`newLine()`), tiện dụng khi xử lý text theo dòng.
- **Cách đọc/ghi**:
    - Dùng `BufferedReader.readLine()` để nhận một chuỗi.
    - Dùng `BufferedWriter.write()` và `flush()` để gửi kết quả.

---

## 3. `datastream` – Làm việc với `DataInputStream` / `DataOutputStream`

- **Lớp sử dụng chính**: `DataInputStream`, `DataOutputStream`
- **Đặc điểm**:
    - Dễ dàng đọc/ghi các kiểu dữ liệu nguyên thủy (int, float, boolean, UTF string,...).
    - Không cần phải phân tích chuỗi thủ công.
    - Giao tiếp hiệu quả, đảm bảo chính xác về kiểu dữ liệu.
- **Cách đọc/ghi**:
    - Dùng các hàm như `readInt()`, `writeUTF()`, `readDouble()`,...

---

## 4. `objectstream` – Làm việc với `ObjectInputStream` / `ObjectOutputStream`

- **Lớp sử dụng chính**: `ObjectInputStream`, `ObjectOutputStream`
- **Đặc điểm**:
    - Cho phép truyền và nhận nguyên các đối tượng Java giữa client/server.
    - Đối tượng phải implements `Serializable`.
    - Giữ nguyên cấu trúc và trạng thái đối tượng (các thuộc tính,...).
- **Cách đọc/ghi**:
    - Dùng `readObject()` để nhận và ép kiểu về lớp cụ thể.
    - Dùng `writeObject()` để gửi lại đối tượng sau xử lý.

### Lưu ý:
- Các lớp định nghĩa đối tượng dùng trong `objectstream` như `Product`, `Laptop`, `Address` được đặt trong package `TCP` tại thư mục gốc (`src/TCP`).
- Các lớp này đều implements `Serializable` để có thể truyền qua mạng bằng `ObjectStream`.

---

## Tổng kết

| Dạng bài       | Lớp sử dụng               | Xử lý dữ liệu        | Đặc điểm nổi bật                          |
|----------------|----------------------------|----------------------|-------------------------------------------|
| `bytestream`   | `InputStream`, `OutputStream` | Byte thô             | Phải xử lý thủ công, tách chuỗi, buffer   |
| `characterstream` | `BufferedReader`, `BufferedWriter` | Chuỗi văn bản        | Dễ đọc/ghi chuỗi, hỗ trợ dòng             |
| `datastream`   | `DataInputStream`, `DataOutputStream` | Kiểu nguyên thủy     | Đọc/ghi số, boolean, UTF chính xác        |
| `objectstream` | `ObjectInputStream`, `ObjectOutputStream` | Đối tượng Java       | Gửi nhận trực tiếp object, cần Serializable |

---

Việc chia rõ theo từng loại luồng giúp sinh viên hiểu sâu hơn cách dữ liệu được xử lý trong Java, từ cấp thấp (byte) đến cấp cao (đối tượng), đồng thời rèn luyện khả năng sử dụng linh hoạt các API trong môi trường TCP socket.
