import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormBilling extends JFrame implements ActionListener {
    private JTextField txtNama;
    private JSpinner spinnerKomputer;
    private JSpinner spinnerDurasi;
    private JButton btnTambah;
    private JButton btnReset;
    private JTextArea areaTransaksi;
    private JLabel lblTotal;
    private ArrayList<Pelanggan> listPelanggan;

    public FormBilling() {
        listPelanggan = new ArrayList<>();

        setTitle("Billing Warnet - Maju Jaya Net");
        setSize(520, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        // Panel Header
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblJudul = new JLabel("BILLING WARNET - MAJU JAYA NET");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 18));
        panelHeader.add(lblJudul);

        // Panel Input
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(3, 2, 8, 8));
        panelInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Data Sesi"),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));

        JLabel lblNama = new JLabel("Nama Pelanggan:");
        lblNama.setFont(new Font("Arial", Font.PLAIN, 12));
        txtNama = new JTextField();
        txtNama.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel lblKomputer = new JLabel("Nomor Komputer:");
        lblKomputer.setFont(new Font("Arial", Font.PLAIN, 12));
        spinnerKomputer = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        JLabel lblDurasi = new JLabel("Durasi (menit):");
        lblDurasi.setFont(new Font("Arial", Font.PLAIN, 12));
        spinnerDurasi = new JSpinner(new SpinnerNumberModel(30, 15, 480, 15));

        panelInput.add(lblNama);
        panelInput.add(txtNama);
        panelInput.add(lblKomputer);
        panelInput.add(spinnerKomputer);
        panelInput.add(lblDurasi);
        panelInput.add(spinnerDurasi);

        // Panel Tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 8));

        btnTambah = new JButton("+ Tambah Sesi");
        btnTambah.setFont(new Font("Arial", Font.BOLD, 12));
        btnTambah.setPreferredSize(new Dimension(140, 32));

        btnReset = new JButton("Reset Semua");
        btnReset.setFont(new Font("Arial", Font.BOLD, 12));
        btnReset.setPreferredSize(new Dimension(140, 32));

        btnTambah.addActionListener(this);
        btnReset.addActionListener(this);

        panelTombol.add(btnTambah);
        panelTombol.add(btnReset);

        // Panel Kiri (input + tombol)
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new BorderLayout(0, 0));
        panelKiri.setPreferredSize(new Dimension(220, 0));
        panelKiri.add(panelInput, BorderLayout.CENTER);
        panelKiri.add(panelTombol, BorderLayout.SOUTH);

        // Area Transaksi
        areaTransaksi = new JTextArea();
        areaTransaksi.setEditable(false);
        areaTransaksi.setFont(new Font("Courier New", Font.PLAIN, 12));
        areaTransaksi.setMargin(new Insets(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(areaTransaksi);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Riwayat Transaksi"));

        // Panel Footer
        JPanel panelFooter = new JPanel();
        panelFooter.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        lblTotal = new JLabel("Total Pendapatan: Rp0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        panelFooter.add(lblTotal);

        add(panelHeader, BorderLayout.NORTH);
        add(panelKiri, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(panelFooter, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambah) {
            String nama = txtNama.getText().trim();
            int noKomputer = (int) spinnerKomputer.getValue();
            int durasi = (int) spinnerDurasi.getValue();

            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama pelanggan harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Pelanggan p = new Pelanggan(nama, noKomputer, durasi);
            listPelanggan.add(p);
            areaTransaksi.append(p.getInfo() + "\n");

            int totalSemua = 0;
            for (Pelanggan pl : listPelanggan) {
                totalSemua += pl.hitungBiaya();
            }
            lblTotal.setText("Total Pendapatan: Rp" + totalSemua);

            JOptionPane.showMessageDialog(this, "Sesi ditambahkan!\nBiaya: Rp" + p.hitungBiaya(), "Sukses", JOptionPane.INFORMATION_MESSAGE);
            txtNama.setText("");

        } else if (e.getSource() == btnReset) {
            txtNama.setText("");
            areaTransaksi.setText("");
            listPelanggan.clear();
            lblTotal.setText("Total Pendapatan: Rp0");
        }
    }
}
