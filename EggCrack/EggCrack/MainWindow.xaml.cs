using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System;
using System.IO;

namespace EggCrack
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        // cannot click until initial storyboard is completed
        private bool canClick = true;
        private bool chopstick = false;

        public MainWindow()
        {
            InitializeComponent();
            Storyboard juju = (Storyboard)Resources["juju"];
            Storyboard rolling = (Storyboard)Resources["EggRoll"];
            Storyboard poking = (Storyboard)Resources["poking"];
            Storyboard rotten = (Storyboard)Resources["gudetama"];
            Storyboard falling = (Storyboard)Resources["falling"];
            Storyboard drop = (Storyboard)Resources["drop"];
            Storyboard chill = (Storyboard)Resources["chilling"];
            Storyboard stop = (Storyboard)Resources["stop"];
            Storyboard letmego = (Storyboard)Resources["letmego"];


            // setting everything to hidden
            pokinggrid.Visibility = Visibility.Hidden;
            rottengrid.Visibility = Visibility.Hidden;
            fallgrid.Visibility = Visibility.Hidden;
            dropgrid.Visibility = Visibility.Hidden;
            chillgrid.Visibility = Visibility.Hidden;
            stopgrid.Visibility = Visibility.Hidden;
            rollinggrid.Visibility = Visibility.Hidden;
            jujugrid.Visibility = Visibility.Hidden;
            maingude.Visibility = Visibility.Hidden;
            maingudegrid.Visibility = Visibility.Hidden;

            //Keyboard handler to close the window when Escape key is pressed.
            KeyUp += (s, e) =>
            {
                if (e.Key == Key.Escape)
                {
                    Close();
                }
            };

            // need to set the "completed" action before the storyboards begin
            // these lines set what happens when the storyboard completes
            rolling.Completed += (s, e) =>
            {
                maingudegrid.Visibility = Visibility.Visible;
                rollinggrid.Visibility = Visibility.Hidden;
                maingude.Visibility = Visibility.Visible;
            };
            juju.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                jujugrid.Visibility = Visibility.Hidden;
                maingude.Visibility = Visibility.Visible;
            };
            poking.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                maingudegrid.Visibility = Visibility.Visible;
                pokinggrid.Visibility = Visibility.Hidden;
            };
            rotten.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                rottengrid.Visibility = Visibility.Hidden;
            };
            ////////
            falling.Completed += (s, e) =>
            {
                fallgrid.Visibility = Visibility.Hidden;

                dropgrid.Visibility = Visibility.Visible;
                drop.Begin();
            };
            drop.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                maingudegrid.Visibility = Visibility.Visible;
                dropgrid.Visibility = Visibility.Hidden;
            };
            chill.Completed += (s, e) =>
            {
                //maingude.Visibility = Visibility.Visible;
                chillgrid.Visibility = Visibility.Hidden;
                rottengrid.Visibility = Visibility.Visible;
                rotten.Begin();
            };
            stop.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                maingudegrid.Visibility = Visibility.Visible;
                stopgrid.Visibility = Visibility.Hidden;
            };
            letmego.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                maingudegrid.Visibility = Visibility.Visible;
                pokinggrid.Visibility = Visibility.Hidden;
            };

            ///////////////////////////////////////////////////////////
            mainegg.PreviewMouseLeftButtonDown += (s, e) =>
            {
                if (canClick && (chopstick == false))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    rollinggrid.Visibility = Visibility.Visible;
                    mainegg.Visibility = Visibility.Hidden;
                    maingude.Visibility = Visibility.Hidden;
                    rolling.Begin();
                }
                canClick = true;
            };

            maingude.MouseLeftButtonDown += (s, e) =>
            {
                if (canClick && (chopstick == false))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    maingude.Visibility = Visibility.Hidden;
                    jujugrid.Visibility = Visibility.Visible;
                    maingudegrid.Visibility = Visibility.Hidden;

                    juju.Begin();
                }
                canClick = true;
            };

            ClickChop.MouseLeftButtonDown += (s, e) =>
            {
                if (canClick && (chopstick == false))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    // define cursor
                    String path = Directory.GetCurrentDirectory();
                    this.Cursor = new Cursor(path + "\\chopsticks.cur");
                    chopstick = true;

                    maingude.Visibility = Visibility.Visible;
                    maingudegrid.Visibility = Visibility.Visible;
                }else if (canClick && (chopstick == true))
                {
                    // in progress
                    canClick = false;
                    chopstick = false;
                    gude.Cursor = Cursors.Arrow;
                    this.Cursor = Cursors.Arrow;
                    maingudefall.Visibility = Visibility.Hidden;
                    maingudelet.Visibility = Visibility.Hidden;
                    maingudepok.Visibility = Visibility.Hidden;
                    maingudestop.Visibility = Visibility.Hidden;
                    maingude.Visibility = Visibility.Visible;
                }
                canClick = true;
            };

            maingudefall.MouseDown += (s, e) =>
            {
                if (canClick && (chopstick == true))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    maingudegrid.Visibility = Visibility.Hidden;
                    fallgrid.Visibility = Visibility.Visible;

                    falling.Begin();
                }
                canClick = true;
            };

            maingudelet.MouseLeftButtonDown += (s, e) =>
            {
                if (canClick && (chopstick == true))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    maingudegrid.Visibility = Visibility.Hidden;
                    lemmego.Visibility = Visibility.Visible;

                    letmego.Begin();
                };
                canClick = true;
            };

            maingudestop.MouseLeftButtonDown += (s, e) =>
            {
                if(canClick && (chopstick == true))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    maingudegrid.Visibility = Visibility.Hidden;
                    stopgrid.Visibility = Visibility.Visible;

                    stop.Begin();
                }
                canClick = true;
            };

            maingudepok.MouseLeftButtonDown += (s, e) =>
            {
                if (canClick && (chopstick == true))
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    maingudegrid.Visibility = Visibility.Hidden;
                    pokinggrid.Visibility = Visibility.Visible;

                    poking.Begin();
                }
                canClick = true;
            };

            
        }
    }
}
