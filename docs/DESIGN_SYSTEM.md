# Calibrey Liquid-Glass Obsidian Design System

The Calibrey design system is engineered to foster sustained, distraction-free academic focus. It moves away from playful, childish gamification paradigms and adopts the sleek, vitreous aesthetics of high-performance scientific instruments, Apple macOS, and modern dark-mode tools.

---

## 🎨 Color Palette & Material Tiers

### 1. Canvas & Foundation
- **Deep Obsidian Base**: `#08090C` — Primary background color for root surfaces.
- **Pure Obsidian Abyss**: `#040507` — Deepest background for modal backdrops and system chrome.
- **Card Acrylic Fill**: `rgba(255, 255, 255, 0.04)` to `rgba(255, 255, 255, 0.08)` — Semi-transparent vitreous card surfaces.

### 2. Specular Sheens & Border Bevels
- **Top Bevel Highlight**: `rgba(255, 255, 255, 0.25)` — 1.dp linear gradient catching the top light vector.
- **Bottom Glass Border**: `rgba(255, 255, 255, 0.05)` — Subtle transition separating surfaces from deep backgrounds.
- **Active Titanium Accent**: `#FFFFFF` — Used sparingly for primary active selectors, high-contrast badges, and primary text.

### 3. Semantic Monochromes
- **Titanium White**: `#F8FAFC` — Primary headings, key metrics, active tab text.
- **Silver Steel**: `#94A3B8` — Body text, subheadings, formula variables.
- **Slate Muted**: `#475569` — Micro metadata, breadcrumb separators, inactive indicators.
- **Subtle Platinum Pill**: `#E2E8F0` — Active segmented control backgrounds with `#0A0D12` inverted text.

---

## 📐 Component Guidelines

### 1. GlassCard
Every card component must have:
- Corner radius: `16.dp` to `20.dp`
- Translucent obsidian fill
- Specular border: `BorderStroke(1.dp, Brush.verticalGradient(listOf(Color(0x33FFFFFF), Color(0x0AFFFFFF))))`
- Minimum inner padding: `16.dp`

### 2. GlassSegmentedControl
- Enclosed inside a pill-shaped container (`CircleShape` or `RoundedCornerShape(24.dp)`).
- Active item rendered as a solid `PlatinumWhite` pill with dark obsidian typography.
- Inactive items rendered with muted slate typography with instantaneous tactile response.

### 3. Status Badges & Academic Insignias
- **No Emojis**: Casual emojis (🎉, 🔥, 🏆) are replaced with crisp Material Symbols (`Icons.Default.Verified`, `Icons.Default.ElectricBolt`, `Icons.Default.AccountTree`).
- **Academic Insignias**: Monogram insignia pills (`SC` for Science, `MT` for Mathematics, `PH` for Physics, `CH` for Chemistry, `BI` for Biology) within high-contrast geometric circles.

### 4. Accessibility & Touch Standards
- Minimum interactive component size: **48dp x 48dp** (`minimumInteractiveComponentSize`).
- Contrast ratio between text and background exceeds WCAG AAA standards (7:1+ for primary text).
- Edge-to-edge system bar integration with transparent navigation bars and status bars.
