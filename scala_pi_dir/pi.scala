package calcpi

object pi {

    def estimate_pi(n: Int): Double = {
        var psum = 0.0d;
        val step = 1d/n;
        
        for (i <-  1 to n) {
            var x = (i - 0.5d) * step
            psum = psum + 4d/(1d + (x * x))
        } 
        
        psum * step
        
    }

/*    
   Scala 3 is a menace.
   So it used to be (Scala 2.x) args would be just arguments, in order as 
   strings.

   so:

   scala calcpi.pi 10000 -> ["10000"]
   scala calcpi.pi       -> []

   In Scala 3(.1.1) for "reasons" the first argument is the name of the class 
   and each subsequent argument is /doubled/.

   so:

   scala calcpi.pi 10000     -> ["calcpi.pi","10000","10000"]
   scala calcpi.pi           -> ["calclpi.pi"]
   scala calcpi.pi 10000 red -> ["calcpi.pi","10000","10000","red","red"]

   As a bonus horror, Scala 3 reports its version as version 2.

Myriad [login12] scala_pi_dir :) > scala
Welcome to Scala 3.1.1 (17.0.2, Java OpenJDK 64-Bit Server VM).
Type in expressions for evaluation. Or try :help.
                                                                               
scala> util.Properties.versionString
val res0: String = version 2.13.6
                                                                               
scala> 

*/
    def two_or_three(args: Array[String]): Int = {
        if (args.length == 0) {
            return 2
        } else if ((args.length >= 1) && (args(0).endsWith(".pi") == false)) {
            return 2
        } else {
            return 3
        }
    }

/*
   This is the portable way to do a main() between Scala 2 + 3 but not as 
   portable as sanity would demand.
 */
    def main(args: Array[String]) = {
        var n = 500000000


        val major_version = two_or_three(args)

        if (major_version == 2) {           // Version 2 and below, sanity
            if (args.length >= 1) {
                n = Integer.parseInt(args(0))
            }
        } else if (major_version == 3) {                            // Version 3+, insanity
            if (args.length > 1) {
                n = Integer.parseInt(args(1))
            }
        } else {
            println(">>> WARNING - Could not determine Scala version.")
            println(">>>           Argument parsing disabled.")
            
        }

        println("Calculating PI using:")
        println("  " + n + " slices")
        println("  1 processes")

        val start = System.currentTimeMillis();

        var mypi = estimate_pi(n)
        
        val stop = System.currentTimeMillis();
        val difference = stop - start;
        val time = difference/1000.0d;
        
        println("Obtained value of PI: " + mypi);
        println("Time taken: " + time + " seconds");
    }
}
