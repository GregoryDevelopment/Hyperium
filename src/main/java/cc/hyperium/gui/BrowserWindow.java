/*
 *  Hypixel Community Client, Client optimized for Hypixel Network
 *     Copyright (C) 2018  Hyperium Dev Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.hyperium.gui;

import cc.hyperium.installer.components.MotionPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import static javafx.concurrent.Worker.State.FAILED;

public class BrowserWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 250;
    private WebEngine engine;

    public BrowserWindow(String url) {
        super.frameInit();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(WIDTH, HEIGHT);
        this.setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        initComponents();
        loadURL(url);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Platform.runLater(()->engine.load(""));
            }
        });
        setTitle("Browser");
        this.setVisible(true);
        this.setLayout(null);
    }

    private void initComponents() {
        Container container = getContentPane();
        container.setLayout(null);

        JFXPanel jfx = new JFXPanel();
        jfx.setBounds(0, 10, getWidth(), getHeight()-10);
        Platform.runLater(()->{
            WebView view = new WebView();
            engine = view.getEngine();
            engine.getLoadWorker()
                    .exceptionProperty()
                    .addListener((o, old, value) -> {
                        if (engine.getLoadWorker().getState() == FAILED)
                            SwingUtilities.invokeLater(() -> {
                                value.printStackTrace();
                                engine.loadContent("<html><h1>Error</h1>\n<p>Failed to load webpage</p>\n<p>"+value.getMessage()+"</p></html>", "text/html");
                            });
                    });
            jfx.setScene(new Scene(view));
        });
        MotionPanel mp = new MotionPanel(this);
        mp.setBounds(0, 0, getWidth(), 10);
        mp.setBackground(new Color(30, 30, 30));
        container.add(jfx);
        container.add(mp);
        setContentPane(container);
    }
    public void loadURL(final String url) {
        Platform.runLater(() -> {
            String tmp = toURL(url);
            if (tmp == null)
                tmp = toURL("https://" + url);
            engine.load(tmp);
        });
    }
    private static String toURL(String str) {
        try {
            return new URL(str).toExternalForm();
        } catch (MalformedURLException exception) {
            return null;
        }
    }
}