/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hao.controller;

import hao.dao.DepartDAO;
import hao.dao.RecordDAO;
import hao.dao.StaffDAO;
import hao.dao.UserDAO;
import hao.model.Depart;
import hao.model.Record;
import hao.model.Staff;
import hao.model.User;
import java.io.File;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Minh Hao
 */
@Controller
public class HomeController {

    private User conUser;

    @Autowired
    private UserDAO daoUser;

    @Autowired
    private DepartDAO daoDepart;

    @Autowired
    private StaffDAO daoStaff;

    @Autowired
    private RecordDAO daoRecord;

    @Autowired
    ServletContext context;
    
    @Autowired
    JavaMailSender mailSender;

    public HomeController() {
    }

// Trang đăng nhập
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("user", new User());
        return "dangnhap";
    }

//Trang login
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String login(ModelMap model, @ModelAttribute("user") User u) {
        User newuser = daoUser.getByUsername(u.getUsername());
        if (newuser.getPassword().equals(u.getPassword())) {
            conUser = newuser;
            model.addAttribute("tenUser", u.getUsername());
            model.addAttribute("listTOP10", daoStaff.gettop10());
            return "home";
        } else {
            return "dangnhap";
        }
    }

// Return trang chủ
    @RequestMapping(value = "trangchu", method = {RequestMethod.GET})
    public String returnHome(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
             try {
                model.addAttribute("listTOP10", daoStaff.gettop10());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "home";
        }
    }

    //View trang USER
    @RequestMapping(value = "danhsachtaikhoan", method = {RequestMethod.GET})
    public String dsTaiKhoan(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("list", daoUser.getUser());
            model.addAttribute("tenUser", conUser.getUsername());
            return "viewAccount";
        }
    }

    //Return trang insert USER
    @RequestMapping(value = "themtaikhoan", method = {RequestMethod.GET})
    public String insertAccount(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("user", new User());
            model.addAttribute("tenUser", conUser.getUsername());
            return "insertAccount";
        }
    }

    // insert USER
    @RequestMapping(value = "themtaikhoan", method = {RequestMethod.POST})
    public String insertAccount(ModelMap model, @ModelAttribute("user") User u, @RequestParam("hinh") MultipartFile image) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
                image.transferTo(new File(filehinhPath));
                u.setImg(image.getOriginalFilename());
                daoUser.insertUser(u);
                model.addAttribute("tenUser", conUser.getUsername());
                model.addAttribute("list", daoUser.getUser());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewAccount";
        }
    }

    /// Xóa USER
    @RequestMapping(value = "xoataikhoan", method = {RequestMethod.GET})
    public String deleteAccount(ModelMap model, @RequestParam("deletethongtin") String username) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                daoUser.deleteUser(username);
                model.addAttribute("list", daoUser.getUser());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewAccount";
        }
    }

    //Lấy thông tin USER
    @RequestMapping(value = "laythongtin", method = {RequestMethod.GET})
    public String laythongtin(ModelMap model, @RequestParam("thongtin") String a) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                model.addAttribute("user", new User());
                model.addAttribute("RO", true);
                model.addAttribute("user1", daoUser.getByUsername(a));
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "updateAccount";
        }
    }

    //Update USER
    @RequestMapping(value = "capnhattaikhoan", method = {RequestMethod.POST})
    public String capnhatTK(ModelMap model, @ModelAttribute("user") User u, @RequestParam("hinh") MultipartFile image) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                String filehinh = "";
                if (image.isEmpty()) {
                    filehinh = daoUser.getByUsername(u.getUsername()).getImg();
                } else {
                    filehinh = image.getOriginalFilename();
                    String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
                    image.transferTo(new File(filehinhPath));
                }
                model.addAttribute("hinh", image.getOriginalFilename());
                u.setImg(filehinh);
                daoUser.updateUser(u);
                model.addAttribute("tenUser", conUser.getUsername());
                model.addAttribute("list", daoUser.getUser());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewAccount";
        }
    }

    //View trang phòng ban
    @RequestMapping(value = "danhsachphongban", method = {RequestMethod.GET})
    public String danhsachphongban(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                model.addAttribute("listDP", daoDepart.getDeparts());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewDepartment";
        }
    }

    //Return trang insert phòng ban
    @RequestMapping(value = "requestinsert", method = {RequestMethod.GET})
    public String themphongban(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                model.addAttribute("depart", new Depart());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "insertDepartment";
        }
    }

    // insert phòng ban
    @RequestMapping(value = "themphongban", method = {RequestMethod.POST})
    public String themphongban(ModelMap model, @ModelAttribute("depart") Depart d, @RequestParam("hinh") MultipartFile image) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
                image.transferTo(new File(filehinhPath));
                d.setDepimg(image.getOriginalFilename());
                daoDepart.insertDepart(d);
                model.addAttribute("tenUser", conUser.getUsername());
                model.addAttribute("listDP", daoDepart.getDeparts());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewDepartment";
        }
    }

    //xóa phòng ban
    @RequestMapping(value = "xoaphongban", method = {RequestMethod.GET})
    public String xoaphongban(ModelMap model, @RequestParam("deletephongban") String id) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            daoDepart.deleteDepart(id);
            model.addAttribute("tenUser", conUser.getUsername());
            model.addAttribute("listDP", daoDepart.getDeparts());
            return "viewDepartment";
        }
    }

    // lấy thông tin phòng ban
    @RequestMapping(value = "laythongtinDP", method = {RequestMethod.GET})
    public String layphongban(ModelMap model, @RequestParam("thongtinDP") String b) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("depart", new Depart());
            model.addAttribute("R1", true);
            model.addAttribute("depart1", daoDepart.getByDepartID(b));
            model.addAttribute("value", daoDepart.getByDepartID(b).getGetCombobox());
            model.addAttribute("tenUser", conUser.getUsername());
            return "updateDepartment";
        }
    }

    //update phòng ban
    @RequestMapping(value = "capnhatphongban", method = {RequestMethod.POST})
    public String capnhatphongban(ModelMap model, @ModelAttribute("depart") Depart d, @RequestParam("hinh") MultipartFile image) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                String filehinh = "";
                if (image.isEmpty()) {
                    filehinh = daoDepart.getByDepartID(d.getDepartid()).getDepimg();
                } else {
                    filehinh = image.getOriginalFilename();
                    String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
                    image.transferTo(new File(filehinhPath));
                }
                model.addAttribute("hinh", image.getOriginalFilename());
                d.setDepimg(filehinh);
                daoDepart.updateDepart(d);
                model.addAttribute("listDP", daoDepart.getDeparts());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewDepartment";
        }
    }

    //View trang nhân viên
    @RequestMapping(value = "danhsachnhanvien", method = {RequestMethod.GET})
    public String danhsachnhanvien(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                model.addAttribute("listStaff", daoStaff.getStaffs());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewStaff";
        }
    }

    //return thêm nhân viên
    @RequestMapping(value = "returnInsertStaff", method = {RequestMethod.GET})
    public String insertStaff(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("staff", new Staff());
            model.addAttribute("tenUser", conUser.getUsername());
            return "insertStaff";
        }
    }

    // insert nhân viên
    @RequestMapping(value = "themnhanvien", method = {RequestMethod.POST})
    public String themnhanvien(ModelMap model, @ModelAttribute("staff") Staff s, @RequestParam("hinh") MultipartFile image) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
                image.transferTo(new File(filehinhPath));
                s.setPhoto(image.getOriginalFilename());
                daoStaff.insertStaff(s);
                model.addAttribute("tenUser", conUser.getUsername());
                model.addAttribute("listStaff", daoStaff.getStaffs());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewStaff";
        }
    }

    //xoa nhan vien
    @RequestMapping(value = "xoanhanvien", method = {RequestMethod.GET})
    public String xoanhanvien(ModelMap model, @RequestParam("deletenhanvien") String id) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            daoStaff.deleteStaff(id);
            model.addAttribute("tenUser", conUser.getUsername());
            model.addAttribute("listStaff", daoStaff.getStaffs());
            return "viewStaff";
        }
    }

    //laynhanvien
    @RequestMapping(value = "laythongtinStaff", method = {RequestMethod.GET})
    public String laynhanvien(ModelMap model, @RequestParam("thongtinStaff") String nv) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("staff", new Staff());
            model.addAttribute("RO", true);
            model.addAttribute("staff1", daoStaff.getByStaffID(nv));
            model.addAttribute("value1", daoStaff.getByStaffID(nv).getGetGioitinh());
            model.addAttribute("value2", daoStaff.getByStaffID(nv).getGetTrangthai());
            model.addAttribute("tenUser", conUser.getUsername());
            return "updateStaff";
        }
    }

    //update nhân viên
    @RequestMapping(value = "capnhatnhanvien", method = {RequestMethod.POST})
    public String capnhatnhanvien(ModelMap model, @ModelAttribute("staff") Staff s, @RequestParam("hinh") MultipartFile image) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                String filehinh = "";
                if (image.isEmpty()) {
                    filehinh = daoStaff.getByStaffID(s.getStaffid()).getPhoto();
                } else {
                    filehinh = image.getOriginalFilename();
                    String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
                    image.transferTo(new File(filehinhPath));
                }
                model.addAttribute("hinh", image.getOriginalFilename());
                s.setPhoto(filehinh);
                daoStaff.updateStaff(s);
                model.addAttribute("listStaff", daoStaff.getStaffs());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewStaff";
        }
    }

    //View trang đánh giá
    @RequestMapping(value = "danhsachdanhgia", method = {RequestMethod.GET})
    public String danhsachDG(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                model.addAttribute("listRecord", daoRecord.getRecords());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewRecord";
        }
    }

    //return thêm đánh giá
    @RequestMapping(value = "returnInsertRecord", method = {RequestMethod.GET})
    public String returnInsertRecord(ModelMap model) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("record", new Record());
            model.addAttribute("tenUser", conUser.getUsername());
            return "insertRecord";
        }
    }

    // insert đánh giá
    @RequestMapping(value = "themdanhgia", method = {RequestMethod.POST})
    public String themdanhgia(ModelMap model, @ModelAttribute("record") Record r) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                daoRecord.insertRecord(r);
                model.addAttribute("listRecord", daoRecord.getRecords());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewRecord";
        }
    }

    //xóa đánh giá
    @RequestMapping(value = "xoandanhgia", method = {RequestMethod.GET})
    public String xoandanhgia(ModelMap model, @RequestParam("madanhgia") String id) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            daoRecord.deleteRecord(id);
            model.addAttribute("tenUser", conUser.getUsername());
            model.addAttribute("listRecord", daoRecord.getRecords());
            return "viewRecord";
        }
    }

    //lấy đánh giá
    @RequestMapping(value = "laythongtinRecord", method = {RequestMethod.GET})
    public String laythongtinRecord(ModelMap model, @RequestParam("maRecord") String rc) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            model.addAttribute("staff", new Staff());
            model.addAttribute("RO", true);
            model.addAttribute("record1", daoRecord.getByRecordID(rc));
            model.addAttribute("valueR", daoRecord.getByRecordID(rc).getGetThanhtich());
            model.addAttribute("tenUser", conUser.getUsername());
            return "updateRecord";
        }
    }

    //update đánh giá
    @RequestMapping(value = "capnhatdanhgia", method = {RequestMethod.POST})
    public String capnhatdanhgia(ModelMap model, @ModelAttribute("record") Record r) {
        if (conUser == null) {
            return "dangnhap";
        } else {
            try {
                daoRecord.updateRecord(r);
                model.addAttribute("listRecord", daoRecord.getRecords());
                model.addAttribute("tenUser", conUser.getUsername());
            } catch (Exception e) {
                model.addAttribute("loi", e.toString());
            }
            return "viewRecord";
        }
    }

@RequestMapping("requestEmail")
    public String emailing(ModelMap m, @ModelAttribute("id") int id) {
        try {
            MimeMessage thisemail = mailSender.createMimeMessage();
            MimeMessageHelper help = new MimeMessageHelper(thisemail, true);
            help.setFrom("haotmps11072@fpt.edu.vn");
            help.setTo(daoStaff.getByStaffID(daoRecord.getByRecordID(id).getStaffid()).getEmail());
            help.setReplyTo("haotmps11072@fpt.edu.vn");
            help.setSubject("THONG BAO THANH TICH");
            help.setText("<h1>Coder Company</h1>"
                    + "<h2>THÔNG BÁO KHEN THƯỞNG / KỶ LUẬT</h2>"
                    + "<h3>Nhân viên: " + daoStaff.getByStaffID(daoRecord.getByRecordID(id).getStaffid()).getStaffname() + " </h3>"
                    + "<h4>Hình thức: <span style=\"color: red\">" + daoRecord.getByRecordID(id).getGetNameThanhtich()+ "</span></h4>"
                    + "<h4>Lý do: <span style=\"color: red\">" + daoRecord.getByRecordID(id).getRecordreason()  + "</span></h4>"
                    + "<h4>Ngày lưu thành tích/kỷ luật: " +  daoRecord.getByRecordID(id).getRecorddate() + "</h4>", true);
            mailSender.send(thisemail);
            m.addAttribute("listRecord", daoRecord.getRecords());
            m.addAttribute("tenUser", conUser.getUsername());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "viewRecord";
    }

//    // Return tổng kết
//    @RequestMapping(value = "tongket", method = {RequestMethod.GET})
//    public String returnTongket(ModelMap model) {
//        if (conUser == null) {
//            return "dangnhap";
//        } else {
//             try {
//                model.addAttribute("listtt", daoRecord.gettt());
////                model.addAttribute("listkl", daoRecord.getkl());
//                model.addAttribute("tenUser", conUser.getUsername());
//            } catch (Exception e) {
//                model.addAttribute("loi", e.toString());
//            }
//            return "tongKet";
//        }
//    }
}
