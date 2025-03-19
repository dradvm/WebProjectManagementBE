CREATE DATABASE QuanLyDuAn;
GO

USE QuanLyDuAn;
GO

CREATE TABLE Quyen (
    maQuyen NVARCHAR(50) PRIMARY KEY,
    tenQuyen NVARCHAR(255) NOT NULL
);
CREATE TABLE NguoiDung (
    maNguoiDung NVARCHAR(50) PRIMARY KEY,
    hoTen NVARCHAR(255) NOT NULL,
    laNam BIT NOT NULL,
    soDienThoai NVARCHAR(15) UNIQUE,
    email NVARCHAR(255) UNIQUE,
    matKhau NVARCHAR(255) NOT NULL,
    maQuyen NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maQuyen) REFERENCES Quyen(maQuyen)
);

CREATE TABLE DuAn (
    maDuAn NVARCHAR(50) PRIMARY KEY,
    tenDuAn NVARCHAR(255) NOT NULL,
    moTa NVARCHAR(MAX),
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    trangThai NVARCHAR(50),
    tienDoHoanThanh FLOAT
);

CREATE TABLE PhieuKhaoSat (
    maPhieuKhaoSat NVARCHAR(50) PRIMARY KEY,
    tenPhieuKhaoSat NVARCHAR(255) NOT NULL,
    lienKet NVARCHAR(MAX),
    ngayGioTao DATETIME DEFAULT GETDATE(),
    ngayGioMo DATETIME,
    ngayGioDong DATETIME,
    maDuAn NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maDuAn) REFERENCES DuAn(maDuAn)
);

CREATE TABLE LoaiCauHoi (
    maLoai NVARCHAR(50) PRIMARY KEY,
    tenLoai NVARCHAR(255) NOT NULL,
    moTa NVARCHAR(MAX)
);

CREATE TABLE CauHoi (
    maCauHoi NVARCHAR(50) PRIMARY KEY,
    cauHoi NVARCHAR(MAX) NOT NULL,
    maLoai NVARCHAR(50) NOT NULL,
    batBuoc BIT NOT NULL,
    moTa NVARCHAR(MAX),
    maPhieuKhaoSat NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maLoai) REFERENCES LoaiCauHoi(maLoai),
    FOREIGN KEY (maPhieuKhaoSat) REFERENCES PhieuKhaoSat(maPhieuKhaoSat)
);

CREATE TABLE CauTraLoi (
    maCauTraLoi NVARCHAR(50) PRIMARY KEY,
    noiDung NVARCHAR(MAX) NOT NULL,
    maCauHoi NVARCHAR(50) NOT NULL,
    maNguoiDung NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maCauHoi) REFERENCES CauHoi(maCauHoi),
    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung)
);

CREATE TABLE LoaiTapTin (
    maLoai NVARCHAR(50) PRIMARY KEY,
    loai NVARCHAR(255) NOT NULL
);

CREATE TABLE TapTin (
    maTapTin NVARCHAR(50) PRIMARY KEY,
    tenTapTin NVARCHAR(255) NOT NULL,
    lienKet NVARCHAR(MAX),
    ngayTao DATE NOT NULL,
    tapTin VARBINARY(MAX),
    maLoai NVARCHAR(50) NOT NULL,
    maDuAn NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maLoai) REFERENCES LoaiTapTin(maLoai),
    FOREIGN KEY (maDuAn) REFERENCES DuAn(maDuAn)
);

-- Ràng buộc mối quan hệ quản lý giữa Người Dùng và Dự Án
CREATE TABLE QuanLyDuAn (
    maNguoiDung NVARCHAR(50),
    maDuAn NVARCHAR(50),
    PRIMARY KEY (maNguoiDung, maDuAn),
    FOREIGN KEY (maNguoiDung) REFERENCES NguoiDung(maNguoiDung),
    FOREIGN KEY (maDuAn) REFERENCES DuAn(maDuAn)
);

insert into quyen values ('A', 'B')


delete from NguoiDung
select * from NguoiDung

update NguoiDung set maQuyen = 'Q2' where maNguoiDung = 'ND000000002'


ALTER TABLE [QuanLyDuAn].[dbo].[PhieuKhaoSat] 
ADD lienKetTraLoi VARCHAR(255);

select * from quyen