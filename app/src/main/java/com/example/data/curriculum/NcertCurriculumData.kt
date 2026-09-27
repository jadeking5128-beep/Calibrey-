package com.example.data.curriculum

import com.example.model.*

object NcertCurriculumData {

  fun getSubjectsForClass(ncertClass: NcertClass): List<Subject> {
    return when (ncertClass) {
      NcertClass.CLASS_9 -> listOf(
        Subject("c9_sci", "Science", "SCI-09", "science", ncertClass, 11),
        Subject("c9_math", "Mathematics", "MATH-09", "calculate", ncertClass, 10)
      )
      NcertClass.CLASS_10 -> listOf(
        Subject("c10_sci", "Science", "SCI-10", "biotech", ncertClass, 13),
        Subject("c10_math", "Mathematics", "MATH-10", "calculate", ncertClass, 14)
      )
      NcertClass.CLASS_11 -> listOf(
        Subject("c11_phy", "Physics", "PHY-11", "flare", ncertClass, 14),
        Subject("c11_chem", "Chemistry", "CHEM-11", "science", ncertClass, 9),
        Subject("c11_math", "Mathematics", "MATH-11", "functions", ncertClass, 14),
        Subject("c11_bio", "Biology", "BIO-11", "eco", ncertClass, 19)
      )
      NcertClass.CLASS_12 -> listOf(
        Subject("c12_phy", "Physics", "PHY-12", "electric_bolt", ncertClass, 14),
        Subject("c12_chem", "Chemistry", "CHEM-12", "science", ncertClass, 10),
        Subject("c12_math", "Mathematics", "MATH-12", "functions", ncertClass, 13),
        Subject("c12_bio", "Biology", "BIO-12", "eco", ncertClass, 13)
      )
    }
  }

  fun getChaptersForSubject(subjectId: String): List<Chapter> {
    return when (subjectId) {
      "c10_sci" -> class10ScienceChapters
      "c10_math" -> class10MathChapters
      "c9_sci" -> class9ScienceChapters
      "c9_math" -> class9MathChapters
      "c11_phy" -> class11PhysicsChapters
      "c12_phy" -> class12PhysicsChapters
      else -> class10ScienceChapters
    }
  }

  // --- CLASS 10 SCIENCE CHAPTERS ---
  val class10ScienceChapters: List<Chapter> = listOf(
    Chapter(
      id = "c10_sci_ch1",
      subjectId = "c10_sci",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 1,
      title = "Chemical Reactions & Equations",
      subtitle = "Balancing reactions, types of reactions, oxidation & reduction",
      summary = "NCERT Chapter 1 explores physical vs chemical changes, balancing chemical equations through the law of conservation of mass, and the 5 major types of chemical reactions.",
      estimatedMinutes = 45,
      difficulty = ChapterDifficulty.FOUNDATIONAL,
      youtubeVideoId = "3yq7z_nB2lE", // Educational NCERT video
      videoTitle = "NCERT Class 10: Chemical Reactions & Equations One-Shot",
      videoDuration = "48:15",
      formulas = listOf(
        "Combination: A + B -> AB",
        "Decomposition: AB -> A + B (Thermal, Electrolytic, Photolytic)",
        "Displacement: A + BC -> AC + B",
        "Double Displacement: AB + CD -> AD + CB",
        "Redox: Oxidation (Loss of e- / Gain of O) & Reduction (Gain of e- / Loss of O)"
      ),
      keyTakeaways = listOf(
        "Matter cannot be created nor destroyed in a chemical reaction.",
        "Precipitation reactions produce an insoluble salt.",
        "Corrosion and rancidity are everyday impacts of oxidation.",
        "Antioxidants are added to fatty foods to prevent oxidation."
      ),
      subtopics = listOf(
        Subtopic(
          id = "c10_sci_ch1_s1",
          chapterId = "c10_sci_ch1",
          subtopicNumber = "1.1",
          title = "Chemical Equations & Balancing",
          summary = "Writing word equations, skeletal equations, and step-by-step balancing using hit-and-trial.",
          contentMarkdown = "A chemical equation represents a chemical reaction using symbols and chemical formulae. According to the Law of Conservation of Mass, the total mass of the elements present in the products must equal the total mass of the elements present in the reactants."
        ),
        Subtopic(
          id = "c10_sci_ch1_s2",
          chapterId = "c10_sci_ch1",
          subtopicNumber = "1.2",
          title = "Types of Chemical Reactions",
          summary = "Combination, Decomposition, Displacement, Double Displacement, Precipitation, Neutralisation.",
          contentMarkdown = "Thermal decomposition of ferrous sulphate gives brown Fe2O3, SO2 and SO3. Quicklime (CaO) reacts vigorously with water releasing heat to form slaked lime (Ca(OH)2)."
        ),
        Subtopic(
          id = "c10_sci_ch1_s3",
          chapterId = "c10_sci_ch1",
          subtopicNumber = "1.3",
          title = "Oxidation, Reduction, Corrosion & Rancidity",
          summary = "Gain or loss of oxygen/hydrogen, rusting of iron, black silver sulphide, green copper carbonate.",
          contentMarkdown = "Oxidation is the gain of oxygen or loss of hydrogen. Reduction is the loss of oxygen or gain of hydrogen. When both occur simultaneously, it is termed a redox reaction."
        )
      )
    ),
    Chapter(
      id = "c10_sci_ch2",
      subjectId = "c10_sci",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 2,
      title = "Acids, Bases & Salts",
      subtitle = "pH scale, indicators, neutralization, common salts",
      summary = "Understand chemical properties of acids and bases, how pH scale determines acidity or basicity, and preparation of Baking Soda, Bleaching Powder, Plaster of Paris.",
      estimatedMinutes = 50,
      difficulty = ChapterDifficulty.CORE,
      youtubeVideoId = "gZz5Z2k_p1w",
      videoTitle = "Class 10 Science Chapter 2: Acids, Bases and Salts Complete",
      videoDuration = "52:40",
      formulas = listOf(
        "Acid + Metal -> Salt + Hydrogen gas (H2)",
        "Acid + Metal Carbonate -> Salt + CO2 + H2O",
        "Acid + Base -> Salt + H2O (Neutralization)",
        "pH = -log[H+]; Neutral = 7, Acidic < 7, Basic > 7",
        "Plaster of Paris: CaSO4.1/2H2O + 1.5 H2O -> CaSO4.2H2O (Gypsum)"
      ),
      keyTakeaways = listOf(
        "Acids produce H+ (aq) ions while bases produce OH- (aq) ions in solution.",
        "Tooth enamel (calcium hydroxyapatite) corrodes when mouth pH falls below 5.5.",
        "Bleaching powder is produced by action of chlorine on dry slaked lime Ca(OH)2.",
        "Washing soda has 10 molecules of water of crystallisation (Na2CO3.10H2O)."
      ),
      subtopics = listOf(
        Subtopic(
          id = "c10_sci_ch2_s1",
          chapterId = "c10_sci_ch2",
          subtopicNumber = "2.1",
          title = "Chemical Properties of Acids & Bases",
          summary = "Reaction with metals, metal oxides, carbonates, and hydrogen carbonates.",
          contentMarkdown = "Acids turn blue litmus red. Bases turn red litmus blue. When an acid reacts with a metal, hydrogen gas is evolved which burns with a pop sound."
        ),
        Subtopic(
          id = "c10_sci_ch2_s2",
          chapterId = "c10_sci_ch2",
          subtopicNumber = "2.2",
          title = "What Do All Acids and Bases Have in Common?",
          summary = "Conductivity in aqueous solution, hydronium ion formation.",
          contentMarkdown = "Dry HCl gas does not change the colour of dry litmus paper because H+ ions are produced only in the presence of water."
        ),
        Subtopic(
          id = "c10_sci_ch2_s3",
          chapterId = "c10_sci_ch2",
          subtopicNumber = "2.3",
          title = "The pH Scale & Everyday Importance",
          summary = "Digestive system pH, soil pH, acid rain, bee sting relief.",
          contentMarkdown = "Living organisms function within a narrow range of pH (7.0 to 7.8). Rain water with pH less than 5.6 is called acid rain."
        ),
        Subtopic(
          id = "c10_sci_ch2_s4",
          chapterId = "c10_sci_ch2",
          subtopicNumber = "2.4",
          title = "Salts of Common Use",
          summary = "Baking soda, washing soda, bleaching powder, plaster of paris.",
          contentMarkdown = "Sodium hydroxide is made by the chlor-alkali process. Chlorine is given off at the anode, and hydrogen gas at the cathode."
        )
      )
    ),
    Chapter(
      id = "c10_sci_ch6",
      subjectId = "c10_sci",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 6,
      title = "Life Processes",
      subtitle = "Nutrition, Respiration, Transportation, and Excretion in plants & humans",
      summary = "The essential metabolic activities required to sustain life. In-depth diagrams of the human heart, nephron, digestive system, stomata, and double circulation.",
      estimatedMinutes = 60,
      difficulty = ChapterDifficulty.CORE,
      youtubeVideoId = "8X7eGg5L7Vw",
      videoTitle = "NCERT Class 10 Life Processes Masterclass",
      videoDuration = "1:15:30",
      formulas = listOf(
        "Photosynthesis: 6CO2 + 12H2O + Sunlight -> C6H12O6 + 6O2 + 6H2O",
        "Aerobic Respiration: Glucose -> Pyruvate (cytoplasm) -> 6CO2 + 6H2O + 38 ATP (mitochondria)",
        "Anaerobic in Yeast: Pyruvate -> Ethanol + CO2 + 2 ATP",
        "Anaerobic in Muscle: Pyruvate -> Lactic Acid + 2 ATP (causes cramps)"
      ),
      keyTakeaways = listOf(
        "Bile juice has no enzymes but emulsifies large fat globules.",
        "Alveoli in lungs provide massive surface area for gas exchange.",
        "Double circulation ensures oxygenated and deoxygenated blood do not mix in 4-chambered heart.",
        "Nephron is the structural and functional filtration unit of the kidney."
      ),
      subtopics = listOf(
        Subtopic("c10_sci_ch6_s1", "c10_sci_ch6", "6.1", "Autotrophic & Heterotrophic Nutrition", "Stomata mechanism, digestive enzymes (pepsin, trypsin, lipase, amylase).", "Stomata open when guard cells swell due to water influx."),
        Subtopic("c10_sci_ch6_s2", "c10_sci_ch6", "6.2", "Respiration in Organisms", "Aerobic vs anaerobic pathways, ATP energy currency, human respiratory tract.", "ATP is broken down giving 30.5 kJ/mol energy."),
        Subtopic("c10_sci_ch6_s3", "c10_sci_ch6", "6.3", "Transportation: Blood, Heart, Xylem & Phloem", "Double circulation, blood pressure, transpirational pull, translocation of sucrose.", "Xylem moves water unidirectionally; phloem translocates nutrients bidirectionally."),
        Subtopic("c10_sci_ch6_s4", "c10_sci_ch6", "6.4", "Excretion & Nephron Structure", "Glomerulus filtration, tubular reabsorption, artificial kidney (hemodialysis).", "Initial filtrate contains glucose, amino acids, salts and major amount of water.")
      )
    ),
    Chapter(
      id = "c10_sci_ch10",
      subjectId = "c10_sci",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 10,
      title = "Light: Reflection & Refraction",
      subtitle = "Spherical mirrors, Snell's law, lens formula, magnification, power of lens",
      summary = "Master ray diagrams for concave and convex mirrors and lenses. Understand sign conventions, focal lengths, real vs virtual images, and optical power.",
      estimatedMinutes = 65,
      difficulty = ChapterDifficulty.ADVANCED,
      youtubeVideoId = "3vUv9O5Fj2k",
      videoTitle = "Light Reflection and Refraction Full NCERT Chapter",
      videoDuration = "1:20:10",
      formulas = listOf(
        "Mirror Formula: 1/f = 1/v + 1/u",
        "Mirror Magnification: m = -v/u = h'/h",
        "Snell's Law: n = sin(i) / sin(r)",
        "Lens Formula: 1/f = 1/v - 1/u",
        "Lens Magnification: m = v/u = h'/h",
        "Power of Lens: P = 1 / f(in meters) [Dioptres, D]"
      ),
      keyTakeaways = listOf(
        "Concave mirror forms real and inverted images except when object is between F and P.",
        "Convex mirrors are used as rear-view mirrors because they always produce erect, diminished images and have a wider field of view.",
        "Refraction occurs because light travels at different speeds in different optical media.",
        "Convex lens has positive focal length and power; concave lens has negative focal length and power."
      ),
      subtopics = listOf(
        Subtopic("c10_sci_ch10_s1", "c10_sci_ch10", "10.1", "Reflection of Light & Spherical Mirrors", "Concave vs Convex, focal point, radius of curvature, principal axis.", "Concave mirrors converge light; convex mirrors diverge light."),
        Subtopic("c10_sci_ch10_s2", "c10_sci_ch10", "10.2", "Refraction & Refractive Index", "Absolute refractive index, speed of light, optical density vs mass density.", "Light bends towards the normal when passing into an optically denser medium."),
        Subtopic("c10_sci_ch10_s3", "c10_sci_ch10", "10.3", "Lenses & Lens Formula", "Convex and concave lenses, sign convention, lens power in Dioptres.", "Power of +2.0 D corresponds to a convex lens of focal length +0.5 m.")
      )
    ),
    Chapter(
      id = "c10_sci_ch12",
      subjectId = "c10_sci",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 12,
      title = "Electricity",
      subtitle = "Electric current, Ohm's law, series & parallel resistors, heating effect, power",
      summary = "Foundational physics chapter covering charge, potential difference, factors affecting resistance, resistivity, Joule's law of heating, and commercial unit of electrical energy.",
      estimatedMinutes = 60,
      difficulty = ChapterDifficulty.ADVANCED,
      youtubeVideoId = "aNl3jL8cT9k",
      videoTitle = "Electricity Class 10 NCERT Chapter in One Shot",
      videoDuration = "1:08:45",
      formulas = listOf(
        "Current: I = Q / t (Ampere)",
        "Potential Difference: V = W / Q (Volt)",
        "Ohm's Law: V = I * R",
        "Resistance: R = ρ * (L / A)",
        "Series Equivalent: Rs = R1 + R2 + R3",
        "Parallel Equivalent: 1/Rp = 1/R1 + 1/R2 + 1/R3",
        "Joule's Heating: H = I^2 * R * t",
        "Electric Power: P = V * I = I^2 * R = V^2 / R (Watts)",
        "1 kWh = 3.6 x 10^6 Joules"
      ),
      keyTakeaways = listOf(
        "Ammeter has low resistance and is connected in series; voltmeter has high resistance and is connected in parallel.",
        "Resistivity (ρ) depends only on the nature of the material and temperature, not on dimensions.",
        "Alloys have higher resistivity and do not oxidize readily at high temperatures, hence used in heating elements.",
        "Electric fuse works on the thermal heating effect of current."
      ),
      subtopics = listOf(
        Subtopic("c10_sci_ch12_s1", "c10_sci_ch12", "12.1", "Current, Charge & Potential Difference", "Flow of electrons opposite to conventional current.", "One Coulomb is equivalent to the charge contained in nearly 6 x 10^18 electrons."),
        Subtopic("c10_sci_ch12_s2", "c10_sci_ch12", "12.2", "Ohm's Law & Factors Affecting Resistance", "Direct proportionality, V-I graph slope = R, resistivity.", "Resistance is directly proportional to length and inversely proportional to area of cross-section."),
        Subtopic("c10_sci_ch12_s3", "c10_sci_ch12", "12.3", "Resistors in Series & Parallel", "Voltage division in series, current division in parallel.", "In parallel circuits, reciprocal of equivalent resistance equals sum of individual reciprocals."),
        Subtopic("c10_sci_ch12_s4", "c10_sci_ch12", "12.4", "Heating Effect & Electric Power", "Joule heating, filament bulbs, fuses, commercial kWh unit.", "Commercial unit 1 kilowatt hour (kWh) is commonly known as 1 'unit' of electricity.")
      )
    )
  )

  // --- CLASS 10 MATHEMATICS CHAPTERS ---
  val class10MathChapters: List<Chapter> = listOf(
    Chapter(
      id = "c10_math_ch1",
      subjectId = "c10_math",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 1,
      title = "Real Numbers",
      subtitle = "Fundamental Theorem of Arithmetic, irrationality proofs, HCF & LCM",
      summary = "Prime factorisation method for HCF and LCM, proving √2, √3, √5 are irrational using contradiction.",
      estimatedMinutes = 40,
      difficulty = ChapterDifficulty.FOUNDATIONAL,
      youtubeVideoId = "Nf4a9X3o8s0",
      videoTitle = "NCERT Class 10 Real Numbers Complete",
      videoDuration = "42:10",
      formulas = listOf(
        "HCF(a, b) * LCM(a, b) = a * b",
        "Fundamental Theorem of Arithmetic: Every composite number can be expressed as a unique product of primes",
        "If prime p divides a^2, then p divides a"
      ),
      keyTakeaways = listOf(
        "Product of two numbers equals product of their HCF and LCM.",
        "Proof by contradiction is used to demonstrate irrationality of √p.",
        "HCF is product of the smallest power of each common prime factor."
      ),
      subtopics = listOf(
        Subtopic("c10_math_ch1_s1", "c10_math_ch1", "1.1", "Fundamental Theorem of Arithmetic", "Prime factorisation of composite numbers.", "Unique representation apart from order of factors."),
        Subtopic("c10_math_ch1_s2", "c10_math_ch1", "1.2", "Revisiting Irrational Numbers", "Proving √3 is irrational.", "Let √3 = a/b where a, b are co-prime integers.")
      )
    ),
    Chapter(
      id = "c10_math_ch4",
      subjectId = "c10_math",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 4,
      title = "Quadratic Equations",
      subtitle = "Standard form, factorization, quadratic formula, nature of roots",
      summary = "Solving ax^2 + bx + c = 0, discriminant D = b^2 - 4ac, real, equal, and distinct roots.",
      estimatedMinutes = 55,
      difficulty = ChapterDifficulty.CORE,
      youtubeVideoId = "3yq7z_nB2lE",
      videoTitle = "Quadratic Equations Class 10 Full Chapter",
      videoDuration = "58:30",
      formulas = listOf(
        "Standard Form: ax^2 + bx + c = 0 (a ≠ 0)",
        "Discriminant: D = b^2 - 4ac",
        "Quadratic Formula: x = (-b ± √D) / (2a)",
        "D > 0: Two distinct real roots",
        "D = 0: Two equal real roots (-b / 2a)",
        "D < 0: No real roots"
      ),
      keyTakeaways = listOf(
        "A quadratic equation has at most two real roots.",
        "Splitting the middle term method requires finding factors whose product is a*c and sum is b.",
        "Word problems involve speed-time, area-perimeter, and age relationships."
      ),
      subtopics = listOf(
        Subtopic("c10_math_ch4_s1", "c10_math_ch4", "4.1", "Standard Form & Factorization", "Finding roots by splitting middle term.", "Zero product property: if A*B=0 then A=0 or B=0."),
        Subtopic("c10_math_ch4_s2", "c10_math_ch4", "4.2", "Quadratic Formula & Nature of Roots", "Sridharacharya formula and Discriminant analysis.", "Discriminant determines nature of roots without solving.")
      )
    ),
    Chapter(
      id = "c10_math_ch8",
      subjectId = "c10_math",
      ncertClass = NcertClass.CLASS_10,
      chapterNumber = 8,
      title = "Introduction to Trigonometry",
      subtitle = "Trigonometric ratios, values of specific angles, fundamental identities",
      summary = "Definitions of sin, cos, tan, cot, sec, cosec on right-angled triangles, values at 0°, 30°, 45°, 60°, 90°, and Pythagorean identities.",
      estimatedMinutes = 60,
      difficulty = ChapterDifficulty.ADVANCED,
      youtubeVideoId = "gZz5Z2k_p1w",
      videoTitle = "Class 10 Trigonometry Complete Revision",
      videoDuration = "1:10:00",
      formulas = listOf(
        "sin θ = Perpendicular / Hypotenuse",
        "cos θ = Base / Hypotenuse",
        "tan θ = Perpendicular / Base = sin θ / cos θ",
        "sin^2 θ + cos^2 θ = 1",
        "1 + tan^2 θ = sec^2 θ",
        "1 + cot^2 θ = cosec^2 θ"
      ),
      keyTakeaways = listOf(
        "Values of sin and cos can never exceed 1.",
        "tan 45° = 1, sin 30° = 1/2, cos 30° = √3/2.",
        "Identities hold true for all angle values between 0° and 90°."
      ),
      subtopics = listOf(
        Subtopic("c10_math_ch8_s1", "c10_math_ch8", "8.1", "Trigonometric Ratios", "Six ratios for acute angles in right triangle.", "Opposite side, adjacent side, hypotenuse."),
        Subtopic("c10_math_ch8_s2", "c10_math_ch8", "8.2", "Trigonometric Identities", "Proving complex trigonometric identities.", "Convert everything into sin and cos as standard strategy.")
      )
    )
  )

  // --- CLASS 9 & 11 SAMPLES ---
  val class9ScienceChapters: List<Chapter> = listOf(
    Chapter(
      id = "c9_sci_ch1",
      subjectId = "c9_sci",
      ncertClass = NcertClass.CLASS_9,
      chapterNumber = 1,
      title = "Matter in Our Surroundings",
      subtitle = "States of matter, latent heat, evaporation",
      summary = "Particle nature of matter, solid-liquid-gas transitions, sublimation, evaporation factors.",
      estimatedMinutes = 40,
      difficulty = ChapterDifficulty.FOUNDATIONAL,
      youtubeVideoId = "3yq7z_nB2lE",
      videoTitle = "Matter in Our Surroundings Class 9 One Shot",
      videoDuration = "45:00",
      formulas = listOf("K = °C + 273.15", "Density = Mass / Volume"),
      keyTakeaways = listOf("Particles of matter have space and attract each other.", "Evaporation causes cooling."),
      subtopics = listOf(
        Subtopic("c9_sci_ch1_s1", "c9_sci_ch1", "1.1", "States of Matter", "Intermolecular forces and kinetic energy.", "Kinetic theory of matter.")
      )
    ),
    Chapter(
      id = "c9_sci_ch8",
      subjectId = "c9_sci",
      ncertClass = NcertClass.CLASS_9,
      chapterNumber = 8,
      title = "Motion",
      subtitle = "Distance, displacement, velocity, acceleration, 3 equations of motion",
      summary = "Kinematics in 1D, graphical derivation of motion equations, uniform circular motion.",
      estimatedMinutes = 55,
      difficulty = ChapterDifficulty.CORE,
      youtubeVideoId = "3yq7z_nB2lE",
      videoTitle = "Motion Class 9 Full Chapter",
      videoDuration = "52:00",
      formulas = listOf("v = u + at", "s = ut + 0.5 at^2", "v^2 = u^2 + 2as"),
      keyTakeaways = listOf("Displacement can be zero even if distance traveled is non-zero.", "Slope of v-t graph gives acceleration."),
      subtopics = listOf(
        Subtopic("c9_sci_ch8_s1", "c9_sci_ch8", "8.1", "Equations of Motion", "Deriving the three kinematic relations.", "Graphical proofs.")
      )
    )
  )

  val class9MathChapters: List<Chapter> = listOf(
    Chapter(
      id = "c9_math_ch1",
      subjectId = "c9_math",
      ncertClass = NcertClass.CLASS_9,
      chapterNumber = 1,
      title = "Number Systems",
      subtitle = "Rational and irrational numbers, laws of exponents",
      summary = "Real numbers on number line, rationalisation of denominator.",
      estimatedMinutes = 45,
      difficulty = ChapterDifficulty.FOUNDATIONAL,
      youtubeVideoId = "Nf4a9X3o8s0",
      videoTitle = "Class 9 Number Systems Complete",
      videoDuration = "40:00",
      formulas = listOf("a^m * a^n = a^(m+n)", "(a^m)^n = a^(mn)"),
      keyTakeaways = listOf("Decimal expansion of rational numbers is terminating or non-terminating recurring."),
      subtopics = listOf(
        Subtopic("c9_math_ch1_s1", "c9_math_ch1", "1.1", "Irrational Numbers", "Locating √2 on number line.", "Pythagorean theorem application.")
      )
    )
  )

  val class11PhysicsChapters: List<Chapter> = listOf(
    Chapter(
      id = "c11_phy_ch3",
      subjectId = "c11_phy",
      ncertClass = NcertClass.CLASS_11,
      chapterNumber = 3,
      title = "Motion in a Straight Line",
      subtitle = "Calculus kinematics, instantaneous velocity & acceleration, stopping distance",
      summary = "Differential & integral calculus approach to 1D kinematics.",
      estimatedMinutes = 60,
      difficulty = ChapterDifficulty.CORE,
      youtubeVideoId = "3yq7z_nB2lE",
      videoTitle = "Class 11 Kinematics 1D",
      videoDuration = "1:05:00",
      formulas = listOf("v = dx/dt", "a = dv/dt = v*(dv/dx)", "x(t) = ∫ v dt"),
      keyTakeaways = listOf("Area under a-t graph gives change in velocity."),
      subtopics = listOf(
        Subtopic("c11_phy_ch3_s1", "c11_phy_ch3", "3.1", "Calculus in Kinematics", "Differentiation of displacement.", "Instantaneous values.")
      )
    )
  )

  val class12PhysicsChapters: List<Chapter> = listOf(
    Chapter(
      id = "c12_phy_ch1",
      subjectId = "c12_phy",
      ncertClass = NcertClass.CLASS_12,
      chapterNumber = 1,
      title = "Electric Charges and Fields",
      subtitle = "Coulomb's law, electric field lines, electric dipole, Gauss's law",
      summary = "Electrostatic force, continuous charge distributions, Gauss theorem and applications.",
      estimatedMinutes = 70,
      difficulty = ChapterDifficulty.ADVANCED,
      youtubeVideoId = "aNl3jL8cT9k",
      videoTitle = "Class 12 Electrostatics One Shot",
      videoDuration = "1:25:00",
      formulas = listOf(
        "F = (1 / 4πε0) * (q1*q2 / r^2)",
        "E = F / q0",
        "Dipole Torque: τ = p x E",
        "Gauss Law: ∮ E · dA = q_enclosed / ε0"
      ),
      keyTakeaways = listOf("Electric field lines never cross each other.", "Field inside a conductor is zero."),
      subtopics = listOf(
        Subtopic("c12_phy_ch1_s1", "c12_phy_ch1", "1.1", "Gauss's Law & Flux", "Flux through closed surfaces.", "Symmetric field evaluations.")
      )
    )
  )

  // --- QUIZZES ---
  fun getQuizzesForChapter(chapterId: String): List<QuizSet> {
    return when (chapterId) {
      "c10_sci_ch1" -> listOf(
        QuizSet(
          id = "q_c10_sci_ch1_foundational",
          chapterId = "c10_sci_ch1",
          chapterTitle = "Chemical Reactions & Equations",
          title = "NCERT Mastery Drill: Equations & Types",
          difficulty = ChapterDifficulty.FOUNDATIONAL,
          rewardGp = 120,
          questions = listOf(
            QuizQuestion(
              id = "q1_1",
              questionText = "Which of the following is NOT an example of a chemical change?",
              options = listOf(
                "Rusting of iron nail",
                "Digestion of food in our body",
                "Melting of ice into water",
                "Fermentation of grapes"
              ),
              correctOptionIndex = 2,
              explanation = "Melting of ice is a physical change because no new chemical substance is formed; water merely changes state from solid to liquid.",
              hint = "Look for a reversible state change.",
              conceptTag = "Physical vs Chemical Change"
            ),
            QuizQuestion(
              id = "q1_2",
              questionText = "When aqueous barium chloride reacts with sodium sulphate, a white precipitate of which substance is formed?",
              options = listOf("Barium sulphate", "Sodium chloride", "Sodium oxide", "Barium sulphite"),
              correctOptionIndex = 0,
              explanation = "BaCl2(aq) + Na2SO4(aq) -> BaSO4(s) + 2NaCl(aq). BaSO4 is insoluble in water and forms a white precipitate.",
              hint = "Double displacement reaction producing an insoluble sulphate.",
              conceptTag = "Precipitation Reactions"
            ),
            QuizQuestion(
              id = "q1_3",
              questionText = "In the reaction: CuO + H2 -> Cu + H2O, which substance acts as the reducing agent?",
              options = listOf("CuO", "H2", "Cu", "H2O"),
              correctOptionIndex = 1,
              explanation = "H2 gains oxygen to form H2O (it is oxidised), so H2 acts as the reducing agent by reducing CuO to Cu.",
              hint = "The reducing agent itself gets oxidised.",
              conceptTag = "Redox Reactions"
            ),
            QuizQuestion(
              id = "q1_4",
              questionText = "Why is respiration considered an exothermic reaction in NCERT Class 10?",
              options = listOf(
                "Oxygen is consumed",
                "Glucose is synthesized",
                "Energy is released in the form of ATP",
                "Carbon dioxide is absorbed"
              ),
              correctOptionIndex = 2,
              explanation = "During digestion, food is broken down into glucose. In cells, glucose combines with oxygen to release heat and cellular energy in the form of ATP.",
              hint = "Think about whether energy is given out or taken in.",
              conceptTag = "Exothermic Reactions"
            )
          )
        ),
        QuizSet(
          id = "q_c10_sci_ch1_advanced",
          chapterId = "c10_sci_ch1",
          chapterTitle = "Chemical Reactions & Equations",
          title = "Board Exam High-Yield Challenge",
          difficulty = ChapterDifficulty.ADVANCED,
          rewardGp = 250,
          questions = listOf(
            QuizQuestion(
              id = "q1_5",
              questionText = "When lead nitrate powder is heated in a boiling tube, brown fumes are emitted. These fumes are of:",
              options = listOf("Nitrogen dioxide (NO2)", "Lead oxide (PbO)", "Oxygen (O2)", "Nitric oxide (NO)"),
              correctOptionIndex = 0,
              explanation = "2Pb(NO3)2 -> 2PbO + 4NO2 + O2. The brown gas evolved is nitrogen dioxide (NO2).",
              hint = "Thermal decomposition of lead nitrate.",
              conceptTag = "Thermal Decomposition"
            ),
            QuizQuestion(
              id = "q1_6",
              questionText = "To balance Fe + H2O -> Fe3O4 + H2, what are the stoichiometric coefficients for Fe, H2O, Fe3O4, and H2 respectively?",
              options = listOf("3, 4, 1, 4", "1, 4, 1, 2", "3, 2, 1, 2", "2, 3, 1, 3"),
              correctOptionIndex = 0,
              explanation = "3Fe + 4H2O -> Fe3O4 + 4H2. Fe: 3 on both sides, H: 8 on both sides, O: 4 on both sides.",
              hint = "First balance Oxygen atoms.",
              conceptTag = "Balancing Equations"
            )
          )
        )
      )
      "c10_sci_ch2" -> listOf(
        QuizSet(
          id = "q_c10_sci_ch2_core",
          chapterId = "c10_sci_ch2",
          chapterTitle = "Acids, Bases & Salts",
          title = "pH & Salt Chemistry Drill",
          difficulty = ChapterDifficulty.CORE,
          rewardGp = 150,
          questions = listOf(
            QuizQuestion(
              id = "q2_1",
              questionText = "What is the chemical formula of Plaster of Paris?",
              options = listOf("CaSO4 · 2H2O", "CaSO4 · 1/2 H2O", "CaSO4 · 5H2O", "CaCO3"),
              correctOptionIndex = 1,
              explanation = "Plaster of Paris is calcium sulphate hemihydrate, CaSO4 · 1/2 H2O.",
              hint = "It has half a molecule of water of crystallisation.",
              conceptTag = "Water of Crystallisation"
            ),
            QuizQuestion(
              id = "q2_2",
              questionText = "A solution turns red litmus paper blue. Its pH is likely to be:",
              options = listOf("1", "4", "5", "10"),
              correctOptionIndex = 3,
              explanation = "Turning red litmus blue is a characteristic of basic solutions. A basic solution has a pH greater than 7.",
              hint = "Bases have pH > 7.",
              conceptTag = "pH Scale"
            )
          )
        )
      )
      "c10_sci_ch12" -> listOf(
        QuizSet(
          id = "q_c10_sci_ch12_core",
          chapterId = "c10_sci_ch12",
          chapterTitle = "Electricity",
          title = "Ohm's Law & Circuit Analysis Drill",
          difficulty = ChapterDifficulty.CORE,
          rewardGp = 180,
          questions = listOf(
            QuizQuestion(
              id = "q12_1",
              questionText = "If three resistors of 6 Ω each are connected in parallel, what is the equivalent resistance?",
              options = listOf("18 Ω", "6 Ω", "2 Ω", "3 Ω"),
              correctOptionIndex = 2,
              explanation = "1/Rp = 1/6 + 1/6 + 1/6 = 3/6 = 1/2, so Rp = 2 Ω.",
              hint = "Use 1/Rp = 1/R1 + 1/R2 + 1/R3.",
              conceptTag = "Parallel Resistors"
            ),
            QuizQuestion(
              id = "q12_2",
              questionText = "Which formula correctly represents Joule's Law of Heating?",
              options = listOf("H = I * R * t", "H = I^2 * R * t", "H = V * I^2 * t", "H = I / (R * t)"),
              correctOptionIndex = 1,
              explanation = "Heat produced in a resistor is directly proportional to square of current, resistance, and time: H = I^2 * R * t.",
              hint = "Proportional to square of electric current.",
              conceptTag = "Joule Heating"
            )
          )
        )
      )
      "c10_math_ch4" -> listOf(
        QuizSet(
          id = "q_c10_math_ch4_core",
          chapterId = "c10_math_ch4",
          chapterTitle = "Quadratic Equations",
          title = "Roots & Discriminant Mastery",
          difficulty = ChapterDifficulty.CORE,
          rewardGp = 160,
          questions = listOf(
            QuizQuestion(
              id = "qm4_1",
              questionText = "For what value of k does 2x^2 + kx + 3 = 0 have two equal real roots?",
              options = listOf("± √24", "± 4", "± 6", "± 2"),
              correctOptionIndex = 0,
              explanation = "For equal roots, D = b^2 - 4ac = 0. So k^2 - 4(2)(3) = 0 => k^2 = 24 => k = ± √24 = ± 2√6.",
              hint = "Set D = b^2 - 4ac = 0.",
              conceptTag = "Discriminant"
            )
          )
        )
      )
      else -> listOf(
        QuizSet(
          id = "q_default_$chapterId",
          chapterId = chapterId,
          chapterTitle = "Chapter Quiz",
          title = "NCERT Quick Assessment",
          difficulty = ChapterDifficulty.CORE,
          rewardGp = 100,
          questions = listOf(
            QuizQuestion(
              id = "qd_1",
              questionText = "Which principle forms the cornerstone of NCERT curriculum in this chapter?",
              options = listOf("Experimental Observation", "Empirical Conservation", "Systematic Deduction", "All of the above"),
              correctOptionIndex = 3,
              explanation = "All core scientific principles in NCERT rely on systematic deduction, empirical conservation laws, and experimental validation.",
              hint = "Comprehensive scientific method.",
              conceptTag = "Core NCERT Concepts"
            )
          )
        )
      )
    }
  }

  // --- KNOWLEDGE GRAPH NODES ---
  fun getKnowledgeNodesForSubject(subjectId: String): List<KnowledgeNode> {
    return when (subjectId) {
      "c10_sci" -> listOf(
        KnowledgeNode("kn_1", "c10_sci_ch1", "Chemical Reactions", "c10_sci", 92, NodeStatus.MASTERED, listOf("kn_2"), "A + B -> AB"),
        KnowledgeNode("kn_2", "c10_sci_ch2", "Acids, Bases & Salts", "c10_sci", 78, NodeStatus.IN_PROGRESS, listOf("kn_3", "kn_6"), "pH = -log[H+]"),
        KnowledgeNode("kn_3", "c10_sci_ch3", "Metals & Non-Metals", "c10_sci", 45, NodeStatus.REVISION_NEEDED, listOf("kn_4"), "Reactivity Series"),
        KnowledgeNode("kn_4", "c10_sci_ch4", "Carbon & Its Compounds", "c10_sci", 30, NodeStatus.WEAK_AREA, listOf("kn_6"), "Catenation & Tetravalency"),
        KnowledgeNode("kn_6", "c10_sci_ch6", "Life Processes", "c10_sci", 85, NodeStatus.MASTERED, listOf("kn_7", "kn_8"), "6CO2 + 6H2O -> C6H12O6"),
        KnowledgeNode("kn_7", "c10_sci_ch7", "Control & Coordination", "c10_sci", 60, NodeStatus.IN_PROGRESS, listOf("kn_8"), "Synapse & Reflex Arc"),
        KnowledgeNode("kn_8", "c10_sci_ch8", "Reproduction", "c10_sci", 70, NodeStatus.IN_PROGRESS, listOf("kn_9"), "DNA Copying"),
        KnowledgeNode("kn_9", "c10_sci_ch9", "Heredity", "c10_sci", 50, NodeStatus.REVISION_NEEDED, listOf("kn_10"), "Mendel Monohybrid 3:1"),
        KnowledgeNode("kn_10", "c10_sci_ch10", "Light: Reflection & Refraction", "c10_sci", 95, NodeStatus.MASTERED, listOf("kn_11", "kn_12"), "1/f = 1/v - 1/u"),
        KnowledgeNode("kn_11", "c10_sci_ch11", "Human Eye & Colorful World", "c10_sci", 65, NodeStatus.IN_PROGRESS, listOf("kn_12"), "Dispersion & Scattering"),
        KnowledgeNode("kn_12", "c10_sci_ch12", "Electricity", "c10_sci", 88, NodeStatus.MASTERED, listOf("kn_13"), "V = I * R"),
        KnowledgeNode("kn_13", "c10_sci_ch13", "Magnetic Effects of Current", "c10_sci", 40, NodeStatus.WEAK_AREA, emptyList(), "Fleming's Left Hand Rule")
      )
      "c10_math" -> listOf(
        KnowledgeNode("km_1", "c10_math_ch1", "Real Numbers", "c10_math", 90, NodeStatus.MASTERED, listOf("km_2"), "HCF * LCM = a * b"),
        KnowledgeNode("km_2", "c10_math_ch2", "Polynomials", "c10_math", 80, NodeStatus.MASTERED, listOf("km_3", "km_4"), "α + β = -b/a"),
        KnowledgeNode("km_3", "c10_math_ch3", "Linear Equations 2V", "c10_math", 65, NodeStatus.IN_PROGRESS, listOf("km_4"), "a1/a2 ≠ b1/b2"),
        KnowledgeNode("km_4", "c10_math_ch4", "Quadratic Equations", "c10_math", 75, NodeStatus.IN_PROGRESS, listOf("km_5", "km_8"), "D = b^2 - 4ac"),
        KnowledgeNode("km_5", "c10_math_ch5", "Arithmetic Progressions", "c10_math", 82, NodeStatus.MASTERED, listOf("km_6"), "an = a + (n-1)d"),
        KnowledgeNode("km_6", "c10_math_ch6", "Triangles", "c10_math", 40, NodeStatus.WEAK_AREA, listOf("km_7", "km_8"), "BPT & Similarity"),
        KnowledgeNode("km_7", "c10_math_ch7", "Coordinate Geometry", "c10_math", 70, NodeStatus.IN_PROGRESS, listOf("km_8"), "√((x2-x1)^2 + (y2-y1)^2)"),
        KnowledgeNode("km_8", "c10_math_ch8", "Trigonometry", "c10_math", 60, NodeStatus.REVISION_NEEDED, emptyList(), "sin^2 θ + cos^2 θ = 1")
      )
      else -> listOf(
        KnowledgeNode("kn_gen1", "gen_1", "Foundations", subjectId, 85, NodeStatus.MASTERED, listOf("kn_gen2"), "Basic Principles"),
        KnowledgeNode("kn_gen2", "gen_2", "Core Application", subjectId, 50, NodeStatus.IN_PROGRESS, emptyList(), "Advanced Formulas")
      )
    }
  }
}
