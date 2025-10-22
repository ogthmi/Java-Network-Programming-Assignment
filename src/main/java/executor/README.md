# Package `executor`

---

## Tổng quan

Dự án được tổ chức thành các package chính:

* **checker**: chứa file main `SampleIO.java` dùng để thử nghiệm và phát triển thuật toán trước khi đưa vào file chuẩn.
* **executor**: chứa các package con tương ứng với các giao thức mạng được sử dụng trong dự án, gồm:

    * `TCP`
    * `UDP`
    * `RMI`
    * `WebService`

Mỗi package con gồm 2 phần chính:

* **Base class**: Là lớp trừu tượng chuẩn hóa việc giao tiếp với server theo giao thức tương ứng, đồng thời định nghĩa khung xử lý bài toán.
* **Runner class**: Là lớp chứa điểm vào (`main`) tạo kết nối, khởi tạo đối tượng bài toán và điều khiển luồng thực thi.

---

## 1. Package `executor.TCP`

### TCPBase (Base class)

* Là lớp abstract cho phép kế thừa.
* Quản lý socket TCP, thiết lập luồng `InputStream` và `OutputStream`.
* Định nghĩa các phương thức abstract để cài đặt luồng dữ liệu, gửi/nhận dữ liệu, và xử lý thuật toán.
* Có phương thức `execute()` gửi mã bài rồi gọi giải thuật.
* Các bài toán TCP kế thừa class này và implement chi tiết logic.

### TcpClientRunner (Runner class)

* Tạo socket TCP tới server dựa trên IP và port lấy từ lớp bài.
* Thiết lập socket cho bài toán.
* Gọi `execute()` để gửi và nhận dữ liệu.
* Đóng socket sau khi xong.

---

## 2. Package `executor.UDP`

### UDPBase (Base class)

* Quản lý đối tượng `DatagramSocket`, địa chỉ IP và cổng UDP.
* Sử dụng `DatagramPacket` để gửi nhận dữ liệu.
* Định nghĩa các phương thức abstract để lấy mã bài, xử lý thuật toán, cùng các hàm gửi và nhận dữ liệu dưới dạng byte.
* Phương thức `execute()` gửi mã bài, gọi hàm giải thuật.

### UdpClientRunner (Runner class)

* Tạo `DatagramSocket` không ràng buộc port cụ thể.
* Khởi tạo lớp bài toán với địa chỉ server, cổng UDP và socket.
* Gọi `execute()` để xử lý.
* Đóng socket sau khi kết thúc.

---

## 3. So sánh TCP và UDP trong `executor`

| Tiêu chí              | TCPBase                                | UDPBase                                       |
| --------------------- | -------------------------------------- | --------------------------------------------- |
| Kết nối               | Sử dụng `Socket`                       | Sử dụng `DatagramSocket`                      |
| Giao tiếp             | Stream (`InputStream`, `OutputStream`) | Gói dữ liệu (`DatagramPacket`)                |
| Cách gửi nhận dữ liệu | Gửi/nhận chuỗi hoặc bytes qua luồng    | Gửi/nhận byte array qua gói dữ liệu           |
| Thiết lập kết nối     | Runner tạo `Socket` rồi truyền cho bài | Runner tạo `DatagramSocket` và truyền cho bài |
| Đặc điểm              | Kết nối, tin cậy                       | Không kết nối, không tin cậy, có thể mất gói  |
| Đóng kết nối          | Đóng socket sau khi xử lý              | Đóng `DatagramSocket` sau khi xử lý           |

---

## 4. Các giao thức khác (RMI, WebService)

* Cấu trúc tương tự với Base class chuẩn hóa giao tiếp theo giao thức.
* Runner khởi tạo client kết nối tương ứng.
* Logic cụ thể bài toán nằm trong lớp kế thừa Base.

---

## 5. Lợi ích của kiến trúc này

* Tách riêng phần giao tiếp mạng (theo từng giao thức) với phần xử lý thuật toán.
* Dễ dàng mở rộng và bảo trì.
* Base class chuẩn hóa các bước chung, Runner tạo điểm vào riêng biệt.
* File test thử (`checker.SampleIO`) giúp phát triển thuận tiện.

---
